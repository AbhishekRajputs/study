package com.example.tokr

import android.content.Context
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar

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

}