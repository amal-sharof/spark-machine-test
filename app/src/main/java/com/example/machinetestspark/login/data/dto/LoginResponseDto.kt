package com.example.machinetestspark.login.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import com.example.machinetestspark.login.domain.model.LoginResponseModel

@Keep
@JsonClass(generateAdapter = true)
data class LoginResponseDto(
    @Json(name = "access")
    val access: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "firstname")
    val firstname: String?,
    @Json(name = "lastname")
    val lastname: String?,
    @Json(name = "refresh")
    val refresh: String?,
    @Json(name = "username")
    val username: String?
){
    fun toLoginResponseModel(): LoginResponseModel{
        return LoginResponseModel(
            userName = username?: "",
            firstName = firstname?: "",
            lastName = lastname?: "",
            email = email?: "",
            authToken = access?: ""
        )
    }
}