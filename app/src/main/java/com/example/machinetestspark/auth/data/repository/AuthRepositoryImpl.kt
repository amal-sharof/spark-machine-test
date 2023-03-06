package com.example.machinetestspark.auth.data.repository

import com.example.machinetestspark.app.common.Constants
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.auth.data.service.AuthService
import com.example.machinetestspark.auth.domain.model.signin.LoginRequestModel
import com.example.machinetestspark.auth.domain.model.signin.LoginResponseModel
import com.example.machinetestspark.auth.domain.repository.AuthRepository
import com.example.machinetestspark.auth.domain.model.signup.SignUpRequestModel
import com.example.machinetestspark.auth.domain.model.signup.SignUpResponseModel
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
): AuthRepository {

    override fun login(loginRequestModel: LoginRequestModel): Flow<Resource<LoginResponseModel>> =
        flow{
            emit(Resource.Loading)
            authService.login(loginRequestModel.toLoginRequestDto())
                .suspendOnSuccess {
                    val responseDto = this.data
                    val response = responseDto.toLoginResponseModel()
                    emit(Resource.Success(response))
                }.suspendOnError {
                    when(this.statusCode) {
                        StatusCode.Unauthorized -> emit(Resource.Error(ERR_LOGIN))
                        StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                        else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
                    }
                }.suspendOnFailure {
                    emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
                }

    }

    override fun signUp(signUpRequestModel: SignUpRequestModel): Flow<Resource<SignUpResponseModel>> =
        flow{
            emit(Resource.Loading)
            authService.signUp(signUpRequestModel.toSignUpRequestDto())
                .suspendOnSuccess {
                    val responseDto = this.data
                    val response =responseDto.toSignUpResponseModel()
                    emit(Resource.Success(response))
                }.suspendOnError {
                    when(this.statusCode) {
                        StatusCode.BadRequest -> emit(Resource.Error(response.message()))
                        StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                        else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
                    }
                }.suspendOnFailure {
                    emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
                }
        }

    companion object {
        const val ERR_LOGIN = "Please enter valid credentials"
    }
}