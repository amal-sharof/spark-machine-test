package com.example.machinetestspark.login.domain.model

import com.example.machinetestspark.login.data.dto.LoginRequestDto

data class LoginRequestModel(
    val userName: String,
    val password: String
){
    fun toLoginRequestDto(): LoginRequestDto{
        return LoginRequestDto(
            username = userName,
            password = password
        )
    }
}
