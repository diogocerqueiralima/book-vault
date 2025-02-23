package com.github.diogocerqueiralima.bookservice.controller

import com.github.diogocerqueiralima.bookservice.dto.ApiResponseDto
import com.github.diogocerqueiralima.bookservice.dto.AuthorCreateDto
import com.github.diogocerqueiralima.bookservice.dto.AuthorDto
import com.github.diogocerqueiralima.bookservice.services.AuthorService
import jakarta.validation.Valid
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api/v1/authors")
class AuthorController(

    private val authorService: AuthorService

) {

    @PostMapping
    fun create(@RequestPart @Valid data: AuthorCreateDto, @RequestPart file: MultipartFile): ResponseEntity<ApiResponseDto<AuthorDto>> {

        val author = authorService.create(data.name, data.about, file)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                ApiResponseDto(
                    message = "Author created successfully",
                    data = AuthorDto(
                        id = author.id,
                        name = author.name,
                        about = author.about,
                        image = "http://localhost:8081/api/v1/authors/${author.id}/image"
                    )
                )
            )
    }

    @GetMapping("/{id}/image")
    fun getImage(@PathVariable id: Long): ResponseEntity<Resource> {

        val image = authorService.getImage(id)
        val headers = HttpHeaders()

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=${image.name}")

        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(ByteArrayResource(image.readBytes()))
    }

}