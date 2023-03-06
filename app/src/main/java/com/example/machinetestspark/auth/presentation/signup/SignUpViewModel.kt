package com.example.machinetestspark.auth.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.auth.domain.model.signup.SignUpRequestModel
import com.example.machinetestspark.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){
    private val _signUpState = MutableStateFlow(SignUpState(loading = false))
    val  signUpState = _signUpState.asStateFlow()


    fun validateSignUp(email: String?, firstName: String?, lastName: String?, userName: String?,
    password: String?, password2: String?) =  viewModelScope.launch{

        when {
            email.isNullOrBlank() -> _signUpState.update { it.copy(error = "Please enter email") }
            firstName.isNullOrBlank() -> _signUpState.update { it.copy(error = "Please enter first name") }
            lastName.isNullOrBlank() -> _signUpState.update { it.copy(error = "Please enter your last name") }
            userName.isNullOrBlank() -> _signUpState.update { it.copy(error = "Please enter your user name") }
            password.isNullOrBlank() -> _signUpState.update { it.copy(error = "Please enter your password") }
            password2.isNullOrBlank() -> _signUpState.update { it.copy(error = "Please confirm your password") }

            else -> {
                val request = SignUpRequestModel(
                    email = email,
                    firstName = firstName,
                    lastName = lastName,
                    userName = userName,
                    password = password,
                    password2 = password2
                )
                signUpRequest(request)
            }
        }

    }

    private suspend fun signUpRequest(signUpRequestModel: SignUpRequestModel){
        authRepository.signUp(signUpRequestModel)
            .collect{responseData ->
                when(responseData) {
                    is Resource.Loading -> handleLoading()
                    is Resource.Success ->{
                        _signUpState.update { it.copy(
                            loading = false,
                            signUpSuccess = responseData.value
                        ) }
                    }
                    is Resource.Error -> handleError(error = responseData.error)
                    else -> Unit
                }
            }
    }

    private fun handleLoading() {
        _signUpState.update {
            it.copy(
                loading = true,
                error = ""
            )
        }
    }

    private fun handleError(error: String) {
        _signUpState.update {
            it.copy(
                loading = false,
                error = error
            )
        }
    }
}