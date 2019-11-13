package com.example.tokr

import android.app.Activity
import android.os.Bundle
import android.app.ActivityOptions
import android.content.Intent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.tokr.imageGridActivity.ImageGridActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
        image_view.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity)
                startActivity(
                    Intent(this@MainActivity, ImageGridActivity::class.java),
                    options.toBundle()
                )
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
    }
}
