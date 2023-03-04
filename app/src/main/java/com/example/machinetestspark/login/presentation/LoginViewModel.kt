package com.example.machinetestspark.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.app.datastore.DataStoreManager
import com.example.machinetestspark.app.datastore.UserDetails
import com.example.machinetestspark.login.domain.model.LoginRequestModel
import com.example.machinetestspark.login.domain.repository.LoginRepository
import com.example.machinetestspark.signup.domain.model.SignUpRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dataStoreManager: DataStoreManager

): ViewModel(){

    private val _loginState = MutableStateFlow(LoginState(loading = false))
    val loginState = _loginState.asStateFlow()


    fun validateLogin( userName: String?,
                       password: String?) =  viewModelScope.launch{

        when {
            userName.isNullOrBlank() -> _loginState.update { it.copy(error = "Please enter your user name") }
            password.isNullOrBlank() -> _loginState.update { it.copy(error = "Please enter your password") }

            else -> {
                val request = LoginRequestModel(
                    userName = userName,
                    password = password,
                )
                loginRequest(request)
            }
        }

    }

    private suspend fun loginRequest(loginRequestModel: LoginRequestModel){
        loginRepository.login(loginRequestModel)
            .collect{responseData ->
                when(responseData) {
                    is Resource.Success ->{
                        _loginState.update { it.copy(
                            loading = false,
                            loginSuccess = responseData.value
                        ) }
                    }
                    is Resource.Error -> handleError(error = responseData.error)
                    else -> {}
                }
            }
    }

    fun saveUserData(userData: UserDetails) = viewModelScope.launch{
        dataStoreManager.saveUserDetails(userData)
    }

    private fun handleError(error: String) {
        _loginState.update {
            it.copy(
                loading = false,
                error = error
            )
        }
    }
}
