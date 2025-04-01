package com.firstprogram.imccalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.firstprogram.imccalculator.ui.theme.IMCCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IMCCalculatorTheme{
                IMCCalculatorApp()
            }
        }
    }
}

@Preview
@Composable
fun IMCCalculatorApp(){
    ResultIMC(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}



@Composable
fun ResultIMC(modifier: Modifier = Modifier){
    var peso by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var result by remember {mutableStateOf("")}

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextField(
            value = peso,
            onValueChange = { peso = it },
            label = { Text("Digite seu peso") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = altura,
            onValueChange = { altura = it },
            label = { Text("Digite seu altura (em metros)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val pesoDouble = peso.toDoubleOrNull()
            val alturaDouble = altura.toDoubleOrNull()

            if (pesoDouble != null && alturaDouble != null && alturaDouble > 0) {
                val imc = pesoDouble / (alturaDouble * alturaDouble)
                result = "Seu IMC é %.2f".format(imc)
            }else{
                result = "Por favor, insira valores válidos"
            }
        }) {
            Text("Calcular")
        }
        Text("Seu imc é $result")
    }
}