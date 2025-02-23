package com.github.diogocerqueiralima.bookservice.exceptions

class AuthorNotFoundException(

    override val message: String = "Author not found"

) : RuntimeException(message)