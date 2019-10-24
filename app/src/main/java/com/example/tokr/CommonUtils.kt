package com.example.tokr

import android.content.Context
import android.widget.Toast

object CommonUtils {

    fun showToast(context: Context, message: String) {
        return Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}