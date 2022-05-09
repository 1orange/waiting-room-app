package com.example.waitingroom.ui.screen.medicalCondition

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.waitingroom.destinations.DetailScreenDestination
import com.example.waitingroom.destinations.PreferenceScreenDestination
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
        LazyColumn() {
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
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(3.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick()
            }
    ) {
        Column {
            Text(
                text = condition.name,
                style = MaterialTheme.typography.h6
            )

            if (condition.description != null){
                Text(
                    text = condition.description,
                    style = MaterialTheme.typography.body1
                )
            } else {
                Text(
                    text = "No description",
                    style = MaterialTheme.typography.body1
                )
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primaryVariant)
                .padding(10.dp)
        ){
            Text(
                text = condition.id.toString(),
                style = MaterialTheme.typography.body1
            )

        }
    }
}


@Composable
fun tmp_tile() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Mild headache",
                style = MaterialTheme.typography.h2
            )

            Text(
                text = "Bearable headache with small fever?",
                style = MaterialTheme.typography.body1
            )
        }

//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .background(B)
//        ){
//
//        }
    }
}