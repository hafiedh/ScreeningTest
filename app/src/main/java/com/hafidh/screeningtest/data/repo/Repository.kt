package com.hafidh.screeningtest.data.repo

import com.hafidh.screeningtest.data.model.Guests
import com.hafidh.screeningtest.data.model.GuestsItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getData(): Flow<ApiResponse<List<GuestsItem>>>
}