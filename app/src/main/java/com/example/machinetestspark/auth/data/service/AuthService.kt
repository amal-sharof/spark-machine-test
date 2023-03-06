package com.example.machinetestspark.auth.data.service

import com.example.machinetestspark.auth.data.dto.signin.LoginRequestDto
import com.example.machinetestspark.auth.data.dto.signin.LoginResponseDto
import com.example.machinetestspark.auth.data.dto.signup.SignUpRequestDto
import com.example.machinetestspark.auth.data.dto.signup.SignUpResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login/")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): ApiResponse<LoginResponseDto>

    @POST("register/")
    suspend fun signUp(@Body signUpRequestDto: SignUpRequestDto): ApiResponse<SignUpResponseDto>
}