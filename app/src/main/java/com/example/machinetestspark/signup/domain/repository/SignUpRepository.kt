package com.example.machinetestspark.signup.domain.repository

import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.signup.domain.model.SignUpRequestModel
import com.example.machinetestspark.signup.domain.model.SignUpResponseModel

interface SignUpRepository {
    fun signUp(signUpRequestModel: SignUpRequestModel): kotlinx.coroutines.flow.Flow<Resource<SignUpResponseModel>>
}