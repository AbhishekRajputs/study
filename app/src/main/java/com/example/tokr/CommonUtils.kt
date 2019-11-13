package com.example.tokr

import android.annotation.SuppressLint
import android.content.Context
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import android.util.TypedValue
import timber.log.Timber
import kotlin.math.roundToInt


object CommonUtils {

    fun showToast(context: Context, message: String) {
        return Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showFullScreen(window: Window, actionBar: ActionBar) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        actionBar.hide()
    }

    @SuppressLint("RtlHardcoded")
    fun slideAnimation(window: Window) {
        val slide = Slide()
        slide.slideEdge = Gravity.LEFT
        slide.duration = 400
        slide.interpolator = DecelerateInterpolator()
        window.exitTransition = slide
        window.enterTransition = slide
    }


    fun explodeAnimation(window: Window) {
        val explode = Explode()
        explode.duration = 1000
        explode.interpolator = DecelerateInterpolator()
        window.exitTransition = explode
        window.enterTransition = explode

    }

}