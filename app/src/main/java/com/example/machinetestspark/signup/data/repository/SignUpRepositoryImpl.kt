package com.example.machinetestspark.signup.data.repository

import com.example.machinetestspark.app.common.Constants
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.signup.data.service.SignUpService
import com.example.machinetestspark.signup.domain.model.SignUpRequestModel
import com.example.machinetestspark.signup.domain.model.SignUpResponseModel
import com.example.machinetestspark.signup.domain.repository.SignUpRepository
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpService: SignUpService
): SignUpRepository{
    override fun signUp(signUpRequestModel: SignUpRequestModel): Flow<Resource<SignUpResponseModel>> =
        flow{
            emit(Resource.Loading)
            signUpService.signUp(signUpRequestModel.toSignUpRequestDto())
                .suspendOnSuccess {
                    val responseDto = this.data
                    val response =responseDto.toSignUpResponseModel()
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