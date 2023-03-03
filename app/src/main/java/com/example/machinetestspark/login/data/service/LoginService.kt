package com.example.machinetestspark.login.data.service

import com.example.machinetestspark.login.data.dto.LoginRequestDto
import com.example.machinetestspark.login.data.dto.LoginResponseDto
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login/")
    suspend fun login(@Body loginRequestDto: LoginRequestDto): ApiResponse<LoginResponseDto>
}