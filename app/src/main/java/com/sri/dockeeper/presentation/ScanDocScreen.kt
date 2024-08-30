package com.sri.dockeeper.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.sri.dockeeper.domain.model.Document
import com.sri.dockeeper.domain.util.Utils.bitmapToByteArray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanDocScreen(navController: NavController, viewModel: ViewModel) {
     var capturedImage by remember {
        mutableStateOf<ImageCapture?>(null)
    }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopBar(
                onAction = { navController.popBackStack() },
                showNavigationIcon = true,
            )
        },
        bottomBar = {
            BottomBar(
                onSaveClick = {
                              capturedImage?.let {
                                captureAndSaveImage(
                                    context = context,
                                    imageCapture = it,
                                    viewModel = viewModel,
                                )
                              } ?: run {
                                  println("---------- vide")
                              }
                },
                navController = navController,
            )
        },
    ) {
        var isPermissionGarentited by remember {
            mutableStateOf(false)
        }
        if (isPermissionGarentited) {
            CameraPreview(onImageCaptured = {
                                            capturedImage = it
            }) {
                Log.e("Error", "Camera is not dispo")
            }
        } else {
            RequestCameraPermission {
                isPermissionGarentited = true
            }
        }
    }
}

@Composable
fun RequestCameraPermission(onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                onPermissionGranted()
            } else {
                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        },
    )

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.CAMERA)
    }
}

@Composable
fun CameraPreview(
    onImageCaptured: (capturedImage: ImageCapture) -> Unit,
    onError: (ImageCaptureException) -> Unit,
) {
    RequestCameraPermission {
    }
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val previewView = remember { PreviewView(context) }
    val imageCapture = remember { ImageCapture.Builder().build() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AndroidView(
            factory = { previewView },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),

            update = { previewView ->
                val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                cameraProviderFuture.addListener({
                    val cameraProvider = cameraProviderFuture.get()
                    val preview = Preview.Builder().build().also {
                        it.setSurfaceProvider(previewView.surfaceProvider)
                    }
                    val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                    try {
                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            imageCapture,
                        )
                    } catch (e: Exception) {
                        onError(
                            ImageCaptureException(
                                ImageCapture.ERROR_UNKNOWN,
                                "Camera binding failed",
                                e,
                            ),
                        )
                    }
                }, ContextCompat.getMainExecutor(context))
            },
        )
        onImageCaptured(imageCapture)
    }
}
fun captureAndSaveImage(context: Context, imageCapture: ImageCapture, viewModel: ViewModel) {
    // Create a temporary file to save the captured image
    val contentResolver = context.contentResolver
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, "captured_image_${System.currentTimeMillis()}.jpg")
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/DocKeeper") // Optional: Define the path within MediaStore
    }

    // Create the output URI using the ContentResolver and MediaStore
    val outputUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    // Check if outputUri is null
    if (outputUri == null) {
        Toast.makeText(context, "Failed to create output file", Toast.LENGTH_SHORT).show()
        return
    }

    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        contentResolver,
        outputUri,
        contentValues,
    ).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                // Image has been saved successfully
                val inputStream = contentResolver.openInputStream(outputUri)
                val bitmap = BitmapFactory.decodeStream(inputStream)

                // Convert Bitmap to ByteArray
                val byteArray = bitmapToByteArray(bitmap)

                // Save image in Room Database via ViewModel
                val doc = Document(title = "test", date = System.currentTimeMillis().toString(), image = byteArray)
                viewModel.insertDoc(doc)

                Toast.makeText(context, "Image Saved Successfully", Toast.LENGTH_SHORT).show()
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraX", "Image capture failed", exception)
                Toast.makeText(context, "Image Capture Failed: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        },
    )
}
