package com.example.authtest

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.example.authtest.ui.theme.AuthTestTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthTestTheme {
                    Greeting()
            }
        }
    }
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Bem-vindo ao programa experimental Firebase"
        )

        Button(onClick = { Intent(Intent.ACTION_MAIN).also {
            it.`package`="com.example.authtest"
            try {
                startActivity(it)
            } catch (e: ActivityNotFoundException){
                e.printStackTrace()
            }
        } })
        {
            Text(text = "Faça seu cadastro")
        }

        Button(onClick = { Intent(Intent.ACTION_MAIN).also {
            it.`package`="com.example.authtest"
            try {
                startActivity(it)
            } catch (e: ActivityNotFoundException){
                e.printStackTrace()
            }
        } }) {
            Text(text = "Entrar")
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    AuthTestTheme {
        Greeting()
    }
}
