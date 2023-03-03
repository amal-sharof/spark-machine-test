package com.example.machinetestspark.login.data.repository

import com.example.machinetestspark.app.common.Constants
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.login.data.service.LoginService
import com.example.machinetestspark.login.domain.model.LoginRequestModel
import com.example.machinetestspark.login.domain.model.LoginResponseModel
import com.example.machinetestspark.login.domain.repository.LoginRepository
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService
): LoginRepository {
    override fun login(loginRequestModel: LoginRequestModel): Flow<Resource<LoginResponseModel>> =
        flow{
            emit(Resource.Loading)
            loginService.login(loginRequestModel.toLoginRequestDto())
                .suspendOnSuccess {
                    val responseDto = this.data
                    val response = responseDto.toLoginResponseModel()
                    emit(Resource.Success(response))
                }.suspendOnError {
                    when(this.statusCode) {
                        StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                        else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
                    }
                }.suspendOnFailure {
                    emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
                }

    }
}