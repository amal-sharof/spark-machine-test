package com.example.machinetestspark.dashboard.data.service

import com.example.machinetestspark.dashboard.data.dto.DashboardResponseDto
import com.skydoves.sandwich.ApiResponse
import okhttp3.Authenticator
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface DashboardService {
    @GET("dashboard/")
    suspend fun dashboard(@Header("Authorization") authToken: String): ApiResponse<List<DashboardResponseDto.DashboardResponseDtoItem>>
}
