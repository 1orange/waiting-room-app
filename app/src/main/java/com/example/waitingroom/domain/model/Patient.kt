package com.example.waitingroom.domain.model

import com.google.gson.annotations.SerializedName

data class Patient (
    val uuid: String,
    val arrived: String,
    val condition_name: String,
    val order_number: Int
)