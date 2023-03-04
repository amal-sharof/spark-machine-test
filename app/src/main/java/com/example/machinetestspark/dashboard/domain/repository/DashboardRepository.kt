package com.example.machinetestspark.dashboard.domain.repository

import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.dashboard.domain.model.DashboardResponseModel
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {

    fun dashboard (authToken: String): Flow<Resource<List<DashboardResponseModel>>>
}