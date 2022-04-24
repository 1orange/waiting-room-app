package com.example.waitingroom.domain.model

import com.google.gson.annotations.SerializedName

data class PatientPOST(
    @SerializedName("condition_id") val condition_id: Int,
)
