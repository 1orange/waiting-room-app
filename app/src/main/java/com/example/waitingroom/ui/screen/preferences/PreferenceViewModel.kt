package com.example.waitingroom.ui.screen.preferences

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.StateEnum
import com.example.waitingroom.domain.repository.WaitingRoomRepositoryInterface
import com.example.waitingroom.ui.screen.details.DetailScreenState
import com.example.waitingroom.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(
    private val repository : WaitingRoomRepositoryInterface,
    handle: SavedStateHandle
) : ViewModel() {

    private val _state: MutableState<DetailScreenState> = mutableStateOf(DetailScreenState(isLoading = true))
    val state: State<DetailScreenState> = _state

    init {
        val patient: Patient? = handle.get("item")
        viewModelScope.launch {
            checkPatientState(patient!!)
        }
    }

    private fun getPatientState(patient: Patient) {
        repository.checkState(patient).onEach { resource ->
            when(resource){
                is Resource.Loading -> {
                    _state.value = DetailScreenState(
                        isLoading = true,
                        patientState = _state.value.patientState,
                    )
                }

                is Resource.Success -> {
                    _state.value = DetailScreenState(
                        isLoading = false,
                        patientState = resource.value!!.state,
                    )

                }

                is Resource.Error -> {
                    _state.value = DetailScreenState(
                        isLoading = false,
                        patientState = _state.value.patientState,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun checkPatientState(patient: Patient) {
        while (_state.value.patientState != StateEnum.SERVED) {
            Log.i("Status:", _state.value.patientState.toString())
            getPatientState(patient = patient)
            delay(5000)
        }
    }
}