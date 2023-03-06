package com.example.machinetestspark.auth.domain.model.signin

data class LoginResponseModel(
    val userName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val authToken: String
)
