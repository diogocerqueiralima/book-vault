package com.github.diogocerqueiralima.bookservice.services

import com.github.diogocerqueiralima.bookservice.domain.Author
import com.github.diogocerqueiralima.bookservice.exceptions.AuthorNotFoundException
import com.github.diogocerqueiralima.bookservice.exceptions.ImageFormatException
import com.github.diogocerqueiralima.bookservice.repositories.AuthorRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.io.path.Path
import kotlin.io.path.createDirectory
import kotlin.io.path.exists

@Service
class AuthorService(

    private val authorRepository: AuthorRepository,

    @Value("\${dir.author}")
    private val uploadDir: String

) {

    fun getById(id: Long): Author =
        authorRepository.findById(id).orElseThrow { AuthorNotFoundException() }

    fun create(name: String, about: String, multipartFile: MultipartFile): Author {

        multipartFile.inputStream.use { inputStream ->

            try {
                ImageIO.read(inputStream)
            }catch (e: IOException) {
                throw ImageFormatException()
            }

        }

        val author = authorRepository.save(
            Author(
                name = name,
                about = about
            )
        )

        val path = Path(uploadDir + File.separator)

        if (!path.exists())
            path.createDirectory()

        multipartFile.transferTo(File(path.toFile(), author.image))

        return author
    }

    fun getImage(id: Long): File {

        val author = getById(id)
        val path = Path(uploadDir + File.separator)

        if (!path.exists())
            path.createDirectory()

        return File(path.toFile(), author.image)
    }

}