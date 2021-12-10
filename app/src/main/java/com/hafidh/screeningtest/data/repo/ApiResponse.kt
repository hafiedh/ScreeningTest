package com.hafidh.screeningtest.data.repo

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val error: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}