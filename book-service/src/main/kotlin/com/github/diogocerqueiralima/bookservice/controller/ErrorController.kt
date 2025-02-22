package com.github.diogocerqueiralima.bookservice.controller

import com.github.diogocerqueiralima.bookservice.dto.ApiResponseDto
import com.github.diogocerqueiralima.bookservice.exceptions.BookNotFoundException
import com.github.diogocerqueiralima.bookservice.exceptions.BookPageException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(BookNotFoundException::class)
    fun handleNotFound(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ApiResponseDto(message = e.message ?: ""))

    @ExceptionHandler(BookPageException::class)
    fun handleBadRequest(e: Exception): ResponseEntity<ApiResponseDto<Unit>> =
        ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ApiResponseDto(message = e.message ?: ""))

}