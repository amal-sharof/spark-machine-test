package com.example.machinetestspark.auth.domain.repository

import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.auth.domain.model.signin.LoginRequestModel
import com.example.machinetestspark.auth.domain.model.signin.LoginResponseModel
import com.example.machinetestspark.auth.domain.model.signup.SignUpRequestModel
import com.example.machinetestspark.auth.domain.model.signup.SignUpResponseModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun login (loginRequestModel: LoginRequestModel): Flow<Resource<LoginResponseModel>>

    fun signUp(signUpRequestModel: SignUpRequestModel): kotlinx.coroutines.flow.Flow<Resource<SignUpResponseModel>>

}