package com.example.machinetestspark.login.domain.repository

import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.login.domain.model.LoginRequestModel
import com.example.machinetestspark.login.domain.model.LoginResponseModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login (loginRequestModel: LoginRequestModel): Flow<Resource<LoginResponseModel>>
}