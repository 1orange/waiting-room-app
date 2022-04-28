package com.example.waitingroom.data.repository

import com.example.waitingroom.data.api.WaitingRoomApi
import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.PatientPOST
import com.example.waitingroom.domain.repository.WaitingRoomRepositoryInterface
import com.example.waitingroom.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WaitingRoomRepository @Inject constructor(
    private val api: WaitingRoomApi
) : WaitingRoomRepositoryInterface {
    override fun getConditionList(): Flow<Resource<List<MedicalCondition>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val conditions = api.getConditionList()
                emit(Resource.Success(conditions))
            }
            catch (e: HttpException) {
                emit(Resource.Error(e.code()))
            }
            catch (e: IOException) {
                emit(Resource.Error(-1))
            }
        }
    }

    override fun enqueuePatient(
        body: PatientPOST
    ): Flow<Resource<Patient>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = api.enqueuePatient(body)
                emit(Resource.Success(response))
            }
            catch (e: HttpException) {
                emit(Resource.Error(e.code()))
            }
            catch (e: IOException) {
                emit(Resource.Error(-1))
            }
        }
    }

    override fun checkOrder(): Flow<Resource<Patient>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = api.getOrder()
                emit(Resource.Success(response))
            }
            catch (e: HttpException) {
                emit(Resource.Error(e.code()))
            }
            catch (e: IOException) {
                emit(Resource.Error(-1))
            }
        }
    }
}