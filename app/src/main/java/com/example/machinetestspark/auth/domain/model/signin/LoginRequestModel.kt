package com.example.machinetestspark.auth.domain.model.signin

import com.example.machinetestspark.auth.data.dto.signin.LoginRequestDto

data class LoginRequestModel(
    val userName: String,
    val password: String
){
    fun toLoginRequestDto(): LoginRequestDto {
        return LoginRequestDto(
            username = userName,
            password = password
        )
    }
}
