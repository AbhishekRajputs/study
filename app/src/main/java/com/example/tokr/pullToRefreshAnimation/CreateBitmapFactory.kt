package com.example.tokr.pullToRefreshAnimation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat

object CreateBitmapFactory {

    private val options by lazy {
        BitmapFactory.Options()
    }

    init {
        options.inPreferredConfig = Bitmap.Config.RGB_565
    }

    fun getBitmapFromImage(@DrawableRes id: Int, context: Context): Bitmap {
        return BitmapFactory.decodeResource(context.resources, id, options)
    }

    fun getBitmapFromDrawable(@DrawableRes id: Int, context: Context): Bitmap? {
        val bitmap: Bitmap
        val drawable =
            ResourcesCompat.getDrawable(context.resources, id, context.theme) ?: return null
        bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}