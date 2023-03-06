package com.example.machinetestspark.auth.data.dto.signup


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class SignUpRequestDto(
    @Json(name = "email")
    val email: String?,
    @Json(name = "first_name")
    val firstName: String?,
    @Json(name = "last_name")
    val lastName: String?,
    @Json(name = "password")
    val password: String?,
    @Json(name = "password2")
    val password2: String?,
    @Json(name = "username")
    val username: String?
)