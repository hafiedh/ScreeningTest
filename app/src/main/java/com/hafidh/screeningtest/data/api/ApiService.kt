package com.hafidh.screeningtest.data.api

import com.hafidh.screeningtest.data.model.GuestsItem
import retrofit2.http.GET

interface ApiService {
    @GET("v2/596dec7f0f000023032b8017")
    suspend fun getGuests(): List<GuestsItem>
}