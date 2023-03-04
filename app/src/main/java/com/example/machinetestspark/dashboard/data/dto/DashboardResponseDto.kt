package com.example.machinetestspark.dashboard.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import com.example.machinetestspark.dashboard.domain.model.DashboardResponseModel

class DashboardResponseDto : ArrayList<DashboardResponseDto.DashboardResponseDtoItem>(){
    @Keep
    @JsonClass(generateAdapter = true)
    data class DashboardResponseDtoItem(
        @Json(name = "id")
        val id: Int?,
        @Json(name = "image_link")
        val imageLink: String?
    ){
        fun toDashboardResponseModel(): DashboardResponseModel{
            return DashboardResponseModel(
                id = id?: -1,
                imageLink = imageLink?: ""
            )
        }
    }
}