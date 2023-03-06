package com.example.machinetestspark.auth.presentation.siginin

import com.example.machinetestspark.auth.domain.model.signin.LoginResponseModel

data class LoginState(
    val loginSuccess: LoginResponseModel? = null,
    val error:String = "",
    val loading: Boolean = false
)
