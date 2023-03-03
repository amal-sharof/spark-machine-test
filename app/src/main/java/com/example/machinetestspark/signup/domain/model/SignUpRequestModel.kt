package com.example.machinetestspark.signup.domain.model

import com.example.machinetestspark.signup.data.dto.SignUpRequestDto

data class SignUpRequestModel(
    val email: String,
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val password2: String
){
    fun toSignUpRequestDto(): SignUpRequestDto{
        return SignUpRequestDto(
            email = email,
            firstName = firstName,
            lastName = lastName,
            username = userName,
            password = password,
            password2 = password2
        )
    }
}
