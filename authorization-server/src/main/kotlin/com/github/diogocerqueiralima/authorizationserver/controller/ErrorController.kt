package com.github.diogocerqueiralima.authorizationserver.controller

import com.github.diogocerqueiralima.authorizationserver.exceptions.RegisterException
import com.github.diogocerqueiralima.authorizationserver.exceptions.UserNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {

    @ExceptionHandler(RegisterException::class)
    fun handleRegisterException(e: RegisterException): String =
        "redirect:/auth/register?error=${e.code}"

    @ExceptionHandler(UserNotFoundException::class)
    fun handleNotFound(e: Exception, http: HttpServletRequest): String =
        "redirect:${http.requestURI}?error"

}