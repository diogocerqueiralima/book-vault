package com.github.diogocerqueiralima.bookservice.exceptions

class ImageFormatException(

    override val message: String = "This is not an image"

) : RuntimeException(message)