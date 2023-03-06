package com.example.machinetestspark.auth.presentation.signup

import com.example.machinetestspark.auth.domain.model.signup.SignUpResponseModel

data class SignUpState(
    val signUpSuccess: SignUpResponseModel? = null,
    val error:String = "",
    val loading: Boolean = false
)
