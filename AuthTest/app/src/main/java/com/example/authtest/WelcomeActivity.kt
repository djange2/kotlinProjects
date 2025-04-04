package com.example.authtest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.authtest.ui.theme.AuthTestTheme
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
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
    val context = androidx.compose.ui.platform.LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bem-vindo ao programa experimental Firebase"
        )

        Button(onClick = {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        })
        {
            Text(text = "Faça seu cadastro")
        }

        Button(onClick = {
            val intent = Intent(context, SignInActivity::class.java)
            context.startActivity(intent)
        })
        {
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
