package com.example.waitingroom.ui.screen.medicalCondition

import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient

data class MedicalConditionState(
    val isLoading: Boolean = false,
    val conditions: List<MedicalCondition> = emptyList(),
    val error: String = "",
    val item: Patient? = null,
    val citizen_id: String? = null,
)