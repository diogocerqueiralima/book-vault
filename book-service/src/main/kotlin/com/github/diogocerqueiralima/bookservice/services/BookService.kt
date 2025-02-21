package com.github.diogocerqueiralima.bookservice.services

import com.github.diogocerqueiralima.bookservice.domain.Book
import com.github.diogocerqueiralima.bookservice.exceptions.BookNotFoundException
import com.github.diogocerqueiralima.bookservice.repositories.BookRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

@Service
class BookService(

    private val bookRepository: BookRepository,

    @Value("\${book.dir}")
    private val uploadDir: String

) {

    fun create(title: String, isbn: String, file: MultipartFile): Book {

        val book = bookRepository.save(
            Book(
                title = title,
                isbn = isbn
            )
        )

        val path = Path(uploadDir + File.separator)

        if (!path.exists())
            path.createDirectory()

        file.transferTo(File(path.toFile(), book.fileName))

        return book
    }

    fun getPdf(id: Long): File {

        val book = bookRepository.findById(id).orElseThrow { BookNotFoundException() }
        val path = Path(uploadDir + File.separator)
        val file = File(path.toFile(), book.fileName)

        return file
    }

}