package com.example.tokr

import android.content.Context
import android.util.TypedValue
import kotlin.math.roundToInt

fun convertDpToPixel(context: Context, dp: Int): Int {
    val r = context.resources
    val px =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
    return px.roundToInt()
}

fun convertDpToFloatPixel(context: Context, dp: Float): Float {
    val r = context.resources
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics)
}



