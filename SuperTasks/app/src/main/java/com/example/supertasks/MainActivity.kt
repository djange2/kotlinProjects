package com.example.supertasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.supertasks.ui.theme.SuperTasksTheme
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperTasksTheme {
                TasksApp()
            }
        }
    }
}

fun addNewTask(name: String, description:String){
    // acessando a intância via SingleTon do Firestore
    val db = Firebase.firestore
    val taskDoc = hashMapOf(
        "name" to name,
        "description" to description
    )
    // armazenar o documento que representa a tarefa na coleção chamada tasks (coleção de documentos)
    // uma coleção representa como se fosse uma "gaveta" para armazenar os documentos (analogia)
    db.collection("tasks").add(taskDoc)
}

// construir a tela com dois campos
// botão para cadastrar a tarefa, que chamará a função


@Preview()
@Composable
fun TasksApp() {
    SuperTasksTheme {
        TaskForm()
    }
}

@Composable
fun TaskForm(modifier: Modifier = Modifier) {
    var name by remember {mutableStateOf("")}
    var description by remember {mutableStateOf("")}

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Digite o nome da tarefa") }
            )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Digite a descrição") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        
    }

}