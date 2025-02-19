package com.github.diogocerqueiralima.authorizationserver.controller

import com.github.diogocerqueiralima.authorizationserver.exceptions.RegisterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {

    @ExceptionHandler
    fun handleRegisterException(e: RegisterException): String =
        "redirect:/auth/register?error=${e.code}"

}