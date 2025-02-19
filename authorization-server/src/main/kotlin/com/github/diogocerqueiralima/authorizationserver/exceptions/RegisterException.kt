package com.github.diogocerqueiralima.authorizationserver.exceptions

open class RegisterException(

    val code: Code,
    override val message: String

) : RuntimeException(message) {

    enum class Code {

        USER_ALREADY_EXISTS,
        PASSWORD_MATCH,
        INVALID_EMAIL,
        INVALID_PASSWORD

    }

}

class PasswordMatchException(

    message: String = "Password does not match"

) : RegisterException(Code.PASSWORD_MATCH, message)

class UserAlreadyExistsException(

    message: String = "User already exists"

) : RegisterException(Code.USER_ALREADY_EXISTS, message)

class EmailException(

    message: String = "Invalid email"

) : RegisterException(Code.INVALID_EMAIL, message)

class PasswordException(

    message: String = "Invalid password"

) : RegisterException(Code.INVALID_PASSWORD, message)