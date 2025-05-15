package br.edu.puc.tirarfotocamera

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraSelector.LensFacing
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
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

@Composable
fun CameraPreview(
    modifier: Modifier = Modifier,
    lensFacing: Int,
    zoomLevel: Float,
    imageCaptureUseCase: ImageCapture
){
    val previewUseCase = remember {
        androidx.camera.core.Preview.Builder().build()
    }

    var cameraProvider by remember {
        mutableStateOf<ProcessCameraProvider?>(null)
    }

    var cameraControl by remember {
        mutableStateOf<CameraControl?>(null)
    }

    // Conteúdo do aplicativo

    val localContext = LocalContext.current

    fun rebindCameraProvider(){
        cameraProvider?.let {cameraProvider ->
            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(lensFacing)
                .build()
            cameraProvider.unbindAll()
            val camera = cameraProvider.bindToLifecycle(
                localContext as LifecycleOwner,
                cameraSelector,
                previewUseCase, imageCaptureUseCase
            )
            cameraControl = camera.cameraControl
        }
    }
    LaunchedEffect(Unit) {
        cameraProvider = ProcessCameraProvider
            .awaitInstance(localContext)
        rebindCameraProvider()
    }

    LaunchedEffect(lensFacing){
        rebindCameraProvider()
    }

    LaunchedEffect (zoomLevel){
        cameraControl?.setLinearZoom(zoomLevel)
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
          factory = { context ->
              PreviewView(context).also {
                  previewUseCase.surfaceProvider = it.surfaceProvider
                  rebindCameraProvider()
              }
          }
    )
}