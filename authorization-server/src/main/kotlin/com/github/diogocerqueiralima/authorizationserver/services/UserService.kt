package com.github.diogocerqueiralima.authorizationserver.services

import com.github.diogocerqueiralima.authorizationserver.domain.User
import com.github.diogocerqueiralima.authorizationserver.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(

    private val userRepository: UserRepository

) : UserDetailsService {

    override fun loadUserByUsername(username: String): User =
        userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User $username not found")

}