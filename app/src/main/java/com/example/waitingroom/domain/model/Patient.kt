package com.example.waitingroom.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Patient (
    val uuid: String,
    val arrived: String,
//    val condition_name: String,
    val order_number: Int
) : Parcelable