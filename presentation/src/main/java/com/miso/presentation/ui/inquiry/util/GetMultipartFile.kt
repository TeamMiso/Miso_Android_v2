package com.miso.presentation.ui.inquiry.util

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

fun getMultipartFile(byteArray: ByteArray): MultipartBody.Part {
    val fileName = "capturedImage.jpg"
    val mediaType = "image/jpeg"
    val byteArray = byteArray.toRequestBody(mediaType.toMediaType())

    return MultipartBody.Part.createFormData("recyclables", fileName, byteArray)
}
fun Uri.toMultipartBody(context: Context): MultipartBody.Part? {
    val file: File? = getFileFromUri(context, this)
    file?.let {
        val requestFile: RequestBody =
            it.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val part: MultipartBody.Part =
            MultipartBody.Part.createFormData("file", it.name, requestFile)
        Log.d("Multipart", file.name)
        return part
    }
    return null
}

fun getFileFromUri(context: Context, uri: Uri): File? {
    val inputStream = context.contentResolver.openInputStream(uri)
    inputStream?.let {
        val fileName = getFileNameFromUri(context, uri)
        val file = File(context.cacheDir, fileName ?: "")
        FileOutputStream(file).use { outputStream ->
            val buffer = ByteArray(4 * 1024) // 4KB buffer size
            var bytesRead: Int
            while (it.read(buffer).also { bytesRead = it } != -1) {
                outputStream.write(buffer, 0, bytesRead)
            }
            outputStream.flush()
        }
        inputStream.close()
        return file
    }
    return null
}

fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val cursor: Cursor? = context.contentResolver.query(uri, null, null, null, null)
    cursor?.let {
        if (it.moveToFirst()) {
            val columIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val displayName = it.getString(columIndex)
            fileName = displayName ?: ""
        }
        cursor.close()
    }
    return fileName
}