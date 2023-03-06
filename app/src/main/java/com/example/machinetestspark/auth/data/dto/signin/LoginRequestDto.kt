package com.example.machinetestspark.auth.data.dto.signin

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class LoginRequestDto(
    @Json(name = "password")
    val password: String?,
    @Json(name = "username")
    val username: String?
)