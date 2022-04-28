package com.example.waitingroom.domain.repository

import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.PatientPOST
import com.example.waitingroom.utils.Resource
import retrofit2.Response
import kotlinx.coroutines.flow.Flow

interface WaitingRoomRepositoryInterface {
    fun getConditionList(): Flow<Resource<List<MedicalCondition>>>
    fun enqueuePatient(body: PatientPOST): Flow<Resource<Patient>>
    fun checkOrder(): Flow<Resource<Patient>>
}