package com.example.machinetestspark.login.domain.model

data class LoginResponseModel(
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val authToken: String
)
