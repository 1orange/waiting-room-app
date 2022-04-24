package com.example.waitingroom.ui.screen.medicalCondition

import com.example.waitingroom.domain.model.MedicalCondition

data class MedicalConditionState(
    val isLoading: Boolean = false,
    val conditions: List<MedicalCondition> = emptyList(),
    val error: String = ""
)