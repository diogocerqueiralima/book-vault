package com.github.diogocerqueiralima.bookservice.exceptions

class BookNotFoundException(

    override val message: String = "Book not found"

) : RuntimeException(message)