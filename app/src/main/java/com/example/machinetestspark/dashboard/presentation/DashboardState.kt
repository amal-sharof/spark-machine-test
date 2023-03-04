package com.example.machinetestspark.dashboard.presentation

import com.example.machinetestspark.dashboard.domain.model.DashboardResponseModel

data class DashboardState(
    val dashboardSuccess: List<DashboardResponseModel>? = null,
    val authToken: String = "",
    val error:String = "",
    val loading: Boolean = false
)
