package com.hafidh.screeningtest.data.repo

import com.hafidh.screeningtest.data.api.ApiService
import com.hafidh.screeningtest.data.model.GuestsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepoImpl @Inject constructor(private val apiService: ApiService) : Repository {
    override suspend fun getData(): Flow<ApiResponse<List<GuestsItem>>> = flow {
        try {
            val response = apiService.getGuests()
            val data = response
            if (data.isNotEmpty()) {
                emit(ApiResponse.Success(data))
            } else emit(ApiResponse.Empty)
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}