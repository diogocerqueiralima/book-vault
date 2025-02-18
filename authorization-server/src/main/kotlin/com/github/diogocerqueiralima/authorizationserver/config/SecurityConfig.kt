package com.github.diogocerqueiralima.authorizationserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Order(1)
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        val authorizationServerConfigurer = OAuth2AuthorizationServerConfigurer.authorizationServer()

        return http
            .securityMatcher(authorizationServerConfigurer.endpointsMatcher)
            .with(authorizationServerConfigurer) { authorizationServer ->
                authorizationServer
                    .oidc(Customizer.withDefaults())
            }
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().authenticated()
            }
            .exceptionHandling { exceptions ->
                exceptions
                    .defaultAuthenticationEntryPointFor(
                        LoginUrlAuthenticationEntryPoint("/login"),
                        MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                    )
            }
            .build()
    }

    @Bean
    @Order(2)
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .anyRequest().authenticated()
            }
            .formLogin(Customizer.withDefaults())
            .build()

    @Bean
    fun passwordEncoder() =
        BCryptPasswordEncoder()

}