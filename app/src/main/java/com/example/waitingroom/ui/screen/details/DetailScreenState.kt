package com.example.waitingroom.ui.screen.details

import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient

data class DetailScreenState(
    val isLoading: Boolean = false,
    val served: Boolean = false,
    val error: String = "",
    val patientState: PatientState = PatientState.WAITING
)

enum class PatientState {
    WAITING,
    INSIDE,
    SERVED,
}