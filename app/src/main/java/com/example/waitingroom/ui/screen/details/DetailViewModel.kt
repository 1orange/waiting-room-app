package com.example.waitingroom.ui.screen.medicalCondition

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.repository.WaitingRoomRepositoryInterface
import com.example.waitingroom.ui.screen.details.DetailScreenState
import com.example.waitingroom.ui.screen.details.PatientState
import com.example.waitingroom.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository : WaitingRoomRepositoryInterface,
    handle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<DetailScreenState> = mutableStateOf(DetailScreenState(isLoading = true))
    val state: State<DetailScreenState> = _state

    init {
        val patient: Patient? = handle.get("item")
        viewModelScope.launch {
            checkOrderNumber(patient)
        }
    }

    private fun getOrderNumber(patient: Patient?) {
        repository.checkOrder().onEach { resource ->
            when(resource){
                is Resource.Loading -> {
                    _state.value = DetailScreenState(
                        isLoading = true,
                        patientState = _state.value.patientState,
                    )
                }

                is Resource.Success -> {
                    if (resource.value?.order_number == patient!!.order_number){
                        if (_state.value.patientState == PatientState.WAITING){
                            _state.value = DetailScreenState(
                                isLoading = false,
                                patientState = PatientState.INSIDE,
                            )
                        }

                    }

                    if (resource.value?.order_number != patient!!.order_number){
                        if (_state.value.patientState == PatientState.INSIDE){
                            _state.value = DetailScreenState(
                                isLoading = false,
                                patientState = PatientState.SERVED,
                            )
                        }
                    }

                    _state.value = DetailScreenState(
                        isLoading = _state.value.isLoading,
                        patientState = _state.value.patientState,
                    )
                }

                is Resource.Error -> {
                    _state.value = DetailScreenState(
                        isLoading = false,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun checkOrderNumber(patient: Patient?) {
        while(_state.value.patientState != PatientState.SERVED) {
            Log.i("Status:", _state.value.patientState.toString())
            getOrderNumber(patient = patient)
            delay(5000)
        }
    }
}