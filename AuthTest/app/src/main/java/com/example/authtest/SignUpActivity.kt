package com.example.authtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.authtest.ui.theme.AuthTestTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignUpActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContent {
            AuthTestTheme {
                SignUpScreen()
            }
        }
    }
}

fun createAccount(
    name: String,
    email: String,
    password: String,
    rg: String,
    cpf: String
){
    val auth = Firebase.auth
    val db = Firebase.firestore

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = auth.currentUser?.uid

                val userMap = hashMapOf(
                    "nome" to name,
                    "email" to email,
                    "rg" to rg,
                    "cpf" to cpf
                )

                userId?.let {
                    db.collection("usuarios").document(it)
                        .set(userMap)
                        .addOnSuccessListener {
                            println("Dados salvos com sucesso!")
                        }
                        .addOnFailureListener { e ->
                            println("Erro ao salvar dados: ${e.message}")
                        }
                }
            } else {
                println("Erro ao criar conta: ${task.exception?.message}")
            }
        }
}

@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var name by remember {mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var RG by remember {mutableStateOf("")}
    var CPF by remember {mutableStateOf("")}

    Column (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Digite o nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Digite o email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Digite a senha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = RG,
            onValueChange = { RG = it },
            label = { Text("Digite o RG") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = CPF,
            onValueChange = { CPF = it },
            label = { Text("Digite o CPF") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { createAccount(name, email, password, RG, CPF) }, modifier = Modifier.fillMaxWidth()){
            Text(text = "Criar")
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    AuthTestTheme {
        SignUpScreen()
    }
}