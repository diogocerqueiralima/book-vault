package com.github.diogocerqueiralima.bookservice.repositories

import com.github.diogocerqueiralima.bookservice.domain.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long>