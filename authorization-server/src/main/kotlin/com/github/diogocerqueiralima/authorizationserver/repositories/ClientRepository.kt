package com.github.diogocerqueiralima.authorizationserver.repositories

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import java.util.*

@Configuration
class ClientRepository(

    @Value("\${client.id}")
    private val clientId: String,

    @Value("\${client.secret}")
    private val clientSecret: String,

    @Value("\${client.uri.redirect}")
    private val clientRedirectUri: String,

    @Value("\${client.uri.logout}")
    private val clientLogoutUri: String,

    private val passwordEncoder: PasswordEncoder

) {

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(clientId)
            .clientSecret(passwordEncoder.encode(clientSecret))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .redirectUri(clientRedirectUri)
            .postLogoutRedirectUri(clientLogoutUri)
            .scope(OidcScopes.OPENID)
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
            .build()

        return InMemoryRegisteredClientRepository(oidcClient)
    }

}