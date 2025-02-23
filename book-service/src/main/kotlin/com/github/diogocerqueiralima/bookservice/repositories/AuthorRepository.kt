package com.github.diogocerqueiralima.bookservice.repositories

import com.github.diogocerqueiralima.bookservice.domain.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long>