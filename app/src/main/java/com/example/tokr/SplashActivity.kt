package com.example.tokr

import android.os.Bundle
import android.app.ActivityOptions
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.tokr.CommonUtils.showFullScreen
import com.example.tokr.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        showFullScreen(window, supportActionBar!!)
        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        image_view.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this@SplashActivity)
                startActivity(
                    Intent(this@SplashActivity, MainActivity::class.java),
                    options.toBundle()
                )
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}
