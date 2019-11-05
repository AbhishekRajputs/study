package com.example.tokr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.ActivityOptions
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.example.tokr.CommonUtils.explodeAnimation
import com.example.tokr.imageActivity.ImageActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_view.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(this)
            startActivity(Intent(this, ImageActivity::class.java), options.toBundle())
        }
    }
}
