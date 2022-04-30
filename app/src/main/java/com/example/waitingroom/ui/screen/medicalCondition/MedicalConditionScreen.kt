package com.example.waitingroom.ui.screen.medicalCondition

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.waitingroom.destinations.DetailScreenDestination
import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.PatientPOST
import com.example.waitingroom.ui.screen.details.DetailScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(start = true)
@Composable
fun MedicalCondition(
    navigator: DestinationsNavigator,
    viewModel: MedicalConditionViewModel = hiltViewModel()
) {

    val state by viewModel.state

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Text("Select your condition.")
    }

    LaunchedEffect(
        state
    ) {
        if (state.item != null) {
            navigator.navigate(DetailScreenDestination(
                state.item!!
            ))
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {}

    Scaffold(
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = {
                        navigator.navigate(PreferenceScreenDestination())
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            if (state.conditions.isEmpty()){
                println("Empty list.")
            } else {
                items(state.conditions) { condition ->
                    MedicalConditionTile(
                        condition = condition,
                        onItemClick = {
                            viewModel.selectItem(
                                PatientPOST(
                                    condition_id = condition.id,
                                    citizen_id = state.citizen_id
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MedicalConditionTile(
    condition: MedicalCondition,
    onItemClick: () -> Unit
) {
    Row(modifier = Modifier
        .padding(all = 8.dp)
        .clickable {
            onItemClick()
        }) {
        Text(text = condition.id.toString(), fontSize = 32.sp)

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = condition.name)

            if (condition.description != null){
                Text(text = condition.description)
            }
        }
    }
}