package com.hafidh.screeningtest.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventItem(
    val name: String,
    val date: String,
    val photo: Int
): Parcelable