package com.github.diogocerqueiralima.bookservice.dto

import jakarta.validation.constraints.NotBlank

data class BookCreateDto(

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val isbn: String

)
