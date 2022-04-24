package com.example.waitingroom.ui.screen.medicalCondition

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waitingroom.domain.repository.WaitingRoomRepositoryInterface
import com.example.waitingroom.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MedicalConditionViewModel @Inject constructor(
    private val repository : WaitingRoomRepositoryInterface
) : ViewModel() {

    private val _state: MutableState<MedicalConditionState> = mutableStateOf(MedicalConditionState(isLoading = true))
    val state: State<MedicalConditionState> = _state

    init {
        updateMedicalConditions()
    }

    private fun updateMedicalConditions() {
        repository.getConditionList().onEach { resource ->
            when(resource){
                is Resource.Loading -> {
                    // TODO: Nejake loading kolecko ukaz
                    _state.value = MedicalConditionState(
                        isLoading = true
                    )
                }

                is Resource.Success -> {
                    _state.value = MedicalConditionState(
                        isLoading = false,
                        conditions = resource.value!!
                    )
                }

                is Resource.Error -> {
                    _state.value = MedicalConditionState(
                        isLoading = false,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}