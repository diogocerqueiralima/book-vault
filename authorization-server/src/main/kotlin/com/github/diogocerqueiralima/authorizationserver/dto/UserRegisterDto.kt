package com.github.diogocerqueiralima.authorizationserver.dto

data class UserRegisterDto(

    val email: String = "",

    val username: String = "",

    val password: String = "",

    val confirmPassword: String = ""

)
