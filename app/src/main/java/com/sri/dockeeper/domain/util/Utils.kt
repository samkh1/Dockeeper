package com.sri.dockeeper.domain.util

import android.graphics.Bitmap
import androidx.camera.core.ImageCapture
import java.io.ByteArrayOutputStream

object Utils {
    // Convert image to byteArray
    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}
