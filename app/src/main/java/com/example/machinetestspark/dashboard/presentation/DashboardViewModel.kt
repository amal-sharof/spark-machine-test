package com.example.machinetestspark.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.app.datastore.DataStoreManager
import com.example.machinetestspark.dashboard.domain.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _dashboardState = MutableStateFlow(DashboardState(loading = false))
    val dashboardState = _dashboardState.asStateFlow()


    /*  *//*init {
        viewModelScope.launch {
            getToken()
        }*//*

    }*/

    fun getToken() = viewModelScope.launch {
        dataStoreManager.getUserFromPreferencesStore().collect { userDetails ->
            _dashboardState.update {
                it.copy(
                    authToken = userDetails.authToken
                )
            }
        }
    }


    fun dashboard(token: String) = viewModelScope.launch {
        dashboardRepository.dashboard(token)
            .collect { responseData ->
                when (responseData) {
                    is Resource.Success -> {
                        _dashboardState.update {
                            it.copy(
                                loading = false,
                                authToken = "",
                                dashboardSuccess = responseData.value
                            )
                        }
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