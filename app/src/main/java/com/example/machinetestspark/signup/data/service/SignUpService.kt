package com.example.machinetestspark.signup.data.service

import com.example.machinetestspark.signup.data.dto.SignUpRequestDto
import com.example.machinetestspark.signup.data.dto.SignUpResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("register/")
    suspend fun signUp(@Body signUpRequestDto: SignUpRequestDto): ApiResponse<SignUpResponseDto>
}