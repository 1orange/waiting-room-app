package com.example.waitingroom.ui.screen.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.patientState == StateEnum.SERVED) {
                Text(
                    text = "Thanks for coming!",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
            } else {
                Column (modifier = Modifier
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = item.order_number.toString(),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = item.arrived,
//                        color = MaterialTheme.colors.secondary,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = item.uuid,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                }
            }
        }
    }
}