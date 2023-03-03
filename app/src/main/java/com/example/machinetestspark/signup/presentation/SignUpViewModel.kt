package com.example.machinetestspark.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.machinetestspark.app.common.Resource
import com.example.machinetestspark.signup.domain.model.SignUpRequestModel
import com.example.machinetestspark.signup.domain.repository.SignUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpRepository: SignUpRepository
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
        signUpRepository.signUp(signUpRequestModel)
            .collect{responseData ->
                when(responseData) {
                    is Resource.Success ->{
                        _signUpState.update { it.copy(
                            loading = false,
                            signUpSuccess = responseData.value
                        ) }
                    }
                    is Resource.Error -> handleError(error = responseData.error)
                    else -> {}
                }
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