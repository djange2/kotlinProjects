package br.edu.puc.tirarfotocamera

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.edu.puc.tirarfotocamera.permissions.WithPermission
import br.edu.puc.tirarfotocamera.ui.theme.TirarFotoCameraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TirarFotoCameraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WithPermission(
                        modifier = Modifier.padding(innerPadding),
                        permission = Manifest.permission.CAMERA,
                        permissionTextButton = "Conceder acesso a câmera"
                        ) {
                            // carregar o composable que abre camera
                            TakePhotoScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun TakePhotoScreen(){
    Text("Exibindo o preview de camera aqui")
}

@Composable
fun RecordAudioScreen(){
    Text("Exibindo a tela do gravador")
}