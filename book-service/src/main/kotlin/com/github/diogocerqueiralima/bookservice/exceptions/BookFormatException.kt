package com.github.diogocerqueiralima.bookservice.exceptions

class BookFormatException(

    override val message: String = "Book format should be PDF"

) : RuntimeException(message)