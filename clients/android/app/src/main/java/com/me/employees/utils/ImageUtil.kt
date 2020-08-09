package com.me.employees.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

object ImageUtil {

    fun getImageBitmap(base64String: String?): Bitmap {
        var imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        var decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        return decodedImage
    }
}