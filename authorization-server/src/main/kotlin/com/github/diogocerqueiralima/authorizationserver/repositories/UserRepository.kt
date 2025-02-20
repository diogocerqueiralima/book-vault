package com.github.diogocerqueiralima.authorizationserver.repositories

import com.github.diogocerqueiralima.authorizationserver.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByUsernameOrEmail(username: String, email: String): User?

    fun findByToken(token: UUID): User?

}