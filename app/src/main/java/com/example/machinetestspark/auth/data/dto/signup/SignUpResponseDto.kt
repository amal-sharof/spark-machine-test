package com.example.machinetestspark.auth.data.dto.signup


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import com.example.machinetestspark.auth.domain.model.signup.SignUpResponseModel

@Keep
@JsonClass(generateAdapter = true)
data class SignUpResponseDto(
    @Json(name = "email")
    val email: String?,
    @Json(name = "first_name")
    val firstName: String?,
    @Json(name = "last_name")
    val lastName: String?,
    @Json(name = "username")
    val username: String?
){
    fun toSignUpResponseModel(): SignUpResponseModel {
        return SignUpResponseModel(
            email = email?: "",
            firstName = firstName?: "",
            lastName = lastName?: "",
            userName = username?: ""
        )
    }
}