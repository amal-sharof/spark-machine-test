package com.example.machinetestspark.app.common

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error( val error: String ) : Resource<Nothing>()
    data class RecommendedError(val errorMessage: String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}
