package com.example.tokr.imageActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tokr.CommonUtils.showFullScreen
import com.example.tokr.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        showFullScreen(window, supportActionBar!!)

        val intent = intent.getStringExtra("imageURL")
        Picasso.with(image_viewer.context).load(intent).fetch(object : Callback {
            override fun onSuccess() {
                image_viewer.alpha = 0f
                Picasso.with(image_viewer.context).load(intent).into(image_viewer)
                image_viewer.animate().setDuration(2000).alpha(1f).start()
            }
            override fun onError() {

            }
        })
    }
}
