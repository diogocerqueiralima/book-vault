package com.github.diogocerqueiralima.authorizationserver.dto

data class UserResetPasswordDto(

    val token: String,
    val password: String = ""

)
