package com.example.waitingroom.domain.model


enum class StateEnum {
    WAITING,
    INSIDE,
    SERVED,
}

data class PatientState (
    val state: StateEnum
)