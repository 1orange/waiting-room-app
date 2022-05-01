package com.example.waitingroom.ui.screen.preferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.example.waitingroom.domain.model.Patient
import com.example.waitingroom.domain.model.StateEnum
import com.example.waitingroom.ui.screen.medicalCondition.DetailViewModel
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun PreferenceScreen(
    navigator: DestinationsNavigator,
    viewModel: PreferenceViewModel = hiltViewModel()
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        label = { Text(text = "Citizen ID") },
        onValueChange = {
            text = it
        }
    )
}