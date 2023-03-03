package com.example.machinetestspark.login.presentation

import com.example.machinetestspark.login.domain.model.LoginResponseModel

data class LoginState(
    val loginSuccess: LoginResponseModel? = null,
    val error:String = "",
    val loading: Boolean = false
)
