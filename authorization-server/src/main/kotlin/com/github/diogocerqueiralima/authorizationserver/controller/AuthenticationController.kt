package com.github.diogocerqueiralima.authorizationserver.controller

import com.github.diogocerqueiralima.authorizationserver.dto.UserForgotPasswordDto
import com.github.diogocerqueiralima.authorizationserver.dto.UserRegisterDto
import com.github.diogocerqueiralima.authorizationserver.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthenticationController(

    private val userService: UserService

) {

    @GetMapping("/login")
    fun login() = "login"

    @GetMapping("/register")
    fun register(model: Model): String {

        model.addAttribute("dto", UserRegisterDto())

        return "register"
    }

    @PostMapping("/register")
    fun register(@ModelAttribute dto: UserRegisterDto): String {

        userService.register(
            email = dto.email,
            username = dto.username,
            password = dto.password,
            confirmPassword = dto.confirmPassword
        )

        return "redirect:/auth/login"
    }

    @GetMapping("/forgot")
    fun forgot(model: Model): String {

        model.addAttribute("dto", UserForgotPasswordDto())

        return "forgot"
    }

    @PostMapping("/forgot")
    fun forgot(@ModelAttribute dto: UserForgotPasswordDto): String {

        userService.forgot(dto.usernameOrEmail)

        return "redirect:/auth/forgot?success"
    }

}