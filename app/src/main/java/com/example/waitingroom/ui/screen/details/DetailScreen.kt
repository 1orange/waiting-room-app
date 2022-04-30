package com.example.waitingroom.ui.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.StateEnum
import com.example.waitingroom.ui.screen.medicalCondition.DetailViewModel
import com.example.waitingroom.ui.screen.medicalCondition.MedicalConditionViewModel
import com.example.waitingroom.ui.theme.WaitingRoomTheme
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun DetailScreen(
    navigator: DestinationsNavigator,
    item: Patient,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state

    if (state.patientState == StateEnum.SERVED) {
        Text("Thanks for coming!")
    } else {
        Column (modifier = Modifier.fillMaxSize()) {
            Text("UUID:"+ item.uuid)
            Text("Poradie:"+ item.order_number)
            Text("Datum:"+ item.arrived)
        }
    }
}