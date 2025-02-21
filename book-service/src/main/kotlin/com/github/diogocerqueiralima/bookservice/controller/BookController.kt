package com.github.diogocerqueiralima.bookservice.controller

import com.github.diogocerqueiralima.bookservice.domain.Book
import com.github.diogocerqueiralima.bookservice.dto.BookCreateDto
import com.github.diogocerqueiralima.bookservice.services.BookService
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/books")
class BookController(

    private val bookService: BookService

) {

    @PostMapping
    fun create(@RequestPart @Valid data: BookCreateDto, @RequestPart file: MultipartFile): ResponseEntity<Book> {

        val book = bookService.create(
            title = data.title,
            isbn = data.isbn,
            file = file
        )

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(book)
    }

    @GetMapping("/{id}/download")
    fun downloadBookPdf(@PathVariable id: Long): ResponseEntity<Resource> {

        val pdf = bookService.getPdf(id)

        val headers = HttpHeaders()
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${pdf.name}")

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(ByteArrayResource(pdf.readBytes()))
    }

}