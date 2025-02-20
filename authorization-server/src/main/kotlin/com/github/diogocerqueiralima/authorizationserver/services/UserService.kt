package com.github.diogocerqueiralima.authorizationserver.services

import com.github.diogocerqueiralima.authorizationserver.domain.User
import com.github.diogocerqueiralima.authorizationserver.exceptions.*
import com.github.diogocerqueiralima.authorizationserver.producers.UserProducer
import com.github.diogocerqueiralima.authorizationserver.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class UserService(

    private val userRepository: UserRepository,
    private val userProducer: UserProducer,
    private val passwordEncoder: PasswordEncoder

) : UserDetailsService {

    override fun loadUserByUsername(username: String): User =
        userRepository.findByUsernameOrEmail(username, username) ?: throw UsernameNotFoundException("User $username not found")

    fun register(email: String, username: String, password: String, confirmPassword: String): User {

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        val pattern = Pattern.compile(emailRegex)

        if (password.isBlank() || password.trim().length < 8)
            throw PasswordException()

        if (password != confirmPassword)
            throw PasswordMatchException()

        if (!pattern.matcher(email).matches())
            throw EmailException()

        val user = userRepository.findByUsernameOrEmail(username, email)

        if (user != null)
            throw UserAlreadyExistsException()

        return userRepository.save(
            User(
                email = email,
                username = username,
                password = passwordEncoder.encode(password)
            )
        )

    }

    fun forgot(usernameOrEmail: String) {

        val user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail) ?: throw UserNotFoundException()

        userProducer.publishEmail(user)
    }

}