package com.example.machinetestspark.dashboard.data.repository

import com.example.machinetestspark.app.common.Constants
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.dashboard.data.service.DashboardService
import com.example.machinetestspark.dashboard.domain.model.DashboardResponseModel
import com.example.machinetestspark.dashboard.domain.repository.DashboardRepository
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
    private val dashboardService: DashboardService
): DashboardRepository{
    override fun dashboard(authToken: String): Flow<Resource<List<DashboardResponseModel>>> =
        flow{
            emit(Resource.Loading)
            dashboardService.dashboard("Bearer $authToken")
                .suspendOnSuccess {
                    val responseDto = this.data
                    val response = responseDto.map { it.toDashboardResponseModel() }
                    emit(Resource.Success(response))
                }.suspendOnError {
                    when(this.statusCode) {
                        StatusCode.InternalServerError -> emit(Resource.Error(Constants.SERVER_ERROR))
                        else -> emit(Resource.Error(Constants.GENERIC_ERROR_MESSAGE))
                    }
                }.suspendOnFailure {
                    emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
                }.suspendOnException {
                    emit(Resource.Error(Constants.NO_INTERNET_ERROR_MESSAGE))
                }
    }
}