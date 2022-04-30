package com.example.waitingroom.ui.screen.details

import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.PatientState
import com.example.waitingroom.domain.model.StateEnum

data class DetailScreenState(
    val isLoading: Boolean = false,
    val served: Boolean = false,
    val error: String = "",
    val patientState: StateEnum = StateEnum.WAITING
)