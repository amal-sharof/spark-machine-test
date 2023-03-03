package com.example.machinetestspark.signup.presentation

import com.example.machinetestspark.signup.domain.model.SignUpResponseModel

data class SignUpState(
    val signUpSuccess: SignUpResponseModel? = null,
    val error:String = "",
    val loading: Boolean = false
)
