package com.github.diogocerqueiralima.authorizationserver.domain

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val email: String,

    @get:JvmName("username")
    val username: String,

    @get:JvmName("password")
    val password: String,

    @ElementCollection(fetch = FetchType.EAGER)
    private val scopes: List<Scope> = emptyList(),

    @Column(unique = true)
    val token: UUID? = null

) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        scopes.map { SimpleGrantedAuthority("SCOPE_$it") }.toMutableList()

    override fun getPassword(): String =
        password

    override fun getUsername(): String =
        username

}
