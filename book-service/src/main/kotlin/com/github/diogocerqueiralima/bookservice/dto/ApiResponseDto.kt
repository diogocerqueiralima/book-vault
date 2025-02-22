package com.github.diogocerqueiralima.bookservice.dto

data class ApiResponseDto<T>(

    val message: String,
    val data: T? = null

)
