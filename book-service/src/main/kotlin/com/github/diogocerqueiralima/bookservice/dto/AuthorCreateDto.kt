package com.github.diogocerqueiralima.bookservice.dto

import jakarta.validation.constraints.NotBlank

data class AuthorCreateDto(

    @field:NotBlank
    val name: String,

    @field:NotBlank
    val about: String

)
