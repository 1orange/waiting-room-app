package com.example.waitingroom.data.api

import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.PatientPOST
import com.example.waitingroom.domain.model.PatientState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface WaitingRoomApi {
    @GET("/conditions")
    suspend fun getConditionList(): List<MedicalCondition>

    @GET("/client")
    suspend fun getOrder(): Patient

    @Headers("Content-Type: application/json")
    @POST("/queue")
    suspend fun checkState(@Body patient: Patient): PatientState

    @Headers("Content-Type: application/json")
    @POST("/client")
    suspend fun enqueuePatient(@Body condition: PatientPOST): Patient
}