package com.github.diogocerqueiralima.bookservice.services

import com.github.diogocerqueiralima.bookservice.domain.Book
import com.github.diogocerqueiralima.bookservice.domain.BookPage
import com.github.diogocerqueiralima.bookservice.exceptions.BookFormatException
import com.github.diogocerqueiralima.bookservice.exceptions.BookNotFoundException
import com.github.diogocerqueiralima.bookservice.exceptions.BookPageException
import com.github.diogocerqueiralima.bookservice.repositories.BookRepository
import org.apache.pdfbox.pdmodel.PDDocument
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

@Service
class BookService(

    private val bookRepository: BookRepository,

    @Value("\${dir.book}")
    private val uploadDir: String

) {

    fun getById(id: Long): Book =
        bookRepository.findById(id).orElseThrow { BookNotFoundException() }

    fun create(title: String, isbn: String, file: MultipartFile): Book {

        try {
            PDDocument.load(file.inputStream)
        }catch (e: IOException) {
            throw BookFormatException()
        }

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

        val book = getById(id)
        val path = Path(uploadDir + File.separator)
        val file = File(path.toFile(), book.fileName)

        return file
    }

    fun getPdfPage(id: Long, page: Int): BookPage {

        if (page < 1)
            throw BookPageException()

        val book = getById(id)
        val path = Path(uploadDir + File.separator)
        val file = File(path.toFile(), book.fileName)

        PDDocument.load(file).use { originalDocument ->

            PDDocument().use { document ->

                if (originalDocument.numberOfPages < page)
                    throw BookPageException()

                val selectedPage = originalDocument.getPage(page - 1)

                document.addPage(selectedPage)

                ByteArrayOutputStream().use { outputStream ->

                    document.save(outputStream)

                    return BookPage(
                        name = "${book.isbn}-page-$page.pdf",
                        bytes = outputStream.toByteArray()
                    )

                }

            }

        }

    }

}