package com.example.waitingroom

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import com.example.waitingroom.domain.model.MedicalCondition
import com.example.waitingroom.domain.repository.WaitingRoomRepositoryInterface
import com.example.waitingroom.ui.theme.WaitingRoomTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WaitingRoomTheme {
//                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {

                }
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }

    }

}

@Destination
@Composable
fun MedicalConditionTile(condition: MedicalCondition) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Text(text = condition.id.toString(), fontSize = 32.sp)

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(text = condition.name)
            Text(text = condition.description)
        }
    }
}

@Preview
@Composable
fun PreviewMedicalConditionTile(){
    MedicalConditionTile(
        condition = MedicalCondition(1, "Test", "Lorem ipsum dolor sit amet")
    )
}

@Composable
fun DisplayMedicalConditions(fetchedConditions: List<MedicalCondition>){
    LazyColumn{
        items(fetchedConditions) { medicalCondition ->
            MedicalConditionTile(condition = medicalCondition)
        }
    }
}