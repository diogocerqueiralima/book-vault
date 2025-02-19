package com.github.diogocerqueiralima.authorizationserver.repositories

import com.github.diogocerqueiralima.authorizationserver.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsername(username: String): User?

    fun findByUsernameOrEmail(username: String, email: String): User?

}