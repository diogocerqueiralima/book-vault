package com.github.diogocerqueiralima.bookservice.exceptions

class BookPageException(

    override val message: String = "The book does not have this page"

) : RuntimeException(message)