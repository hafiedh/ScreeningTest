package com.hafidh.screeningtest.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hafidh.screeningtest.data.model.GuestsItem
import com.hafidh.screeningtest.data.repo.ApiResponse
import com.hafidh.screeningtest.data.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuestViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _guest = MutableStateFlow<UiData>(UiData.Init)
    val guest = _guest.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            repository.getData()
                .collect { result ->
                    when (result) {
                        is ApiResponse.Success -> _guest.value = UiData.Success(result.data)
                        is ApiResponse.Error -> _guest.value = UiData.Error(result.error)
                        else -> _guest.value = UiData.Success(emptyList())
                    }
                }
        }
    }


    sealed class UiData {
        object Init : UiData()
        data class Success(val data: List<GuestsItem>) : UiData()
        data class Error(val error: String) : UiData()
    }

}