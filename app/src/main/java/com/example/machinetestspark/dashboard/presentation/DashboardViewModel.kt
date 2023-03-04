package com.example.machinetestspark.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.dashboard.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel(){

    private val _dashboardState = MutableStateFlow(DashboardState(loading = false))
    val dashboardState  =_dashboardState.asStateFlow()



    private fun dashboard(authToken: String) = viewModelScope.launch{
        dashboardRepository.dashboard(authToken)
            .collect{responseData ->
                when(responseData) {
                    is Resource.Success ->{
                        _dashboardState.update { it.copy(
                            loading = false,
                            dashboardSuccess = responseData.value
                        ) }
                    }
                    is Resource.Error -> handleError(error = responseData.error)
                    else -> Unit
                }
            }
    }

    private fun handleError(error: String) {
        _dashboardState.update {
            it.copy(
                loading = false,
                error = error
            )
        }
    }
}