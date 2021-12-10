package com.hafidh.screeningtest.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GuestsItem(
    @SerializedName("birthdate")
    val birthdate: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?
)