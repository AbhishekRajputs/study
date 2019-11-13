package com.example.tokr.imageActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tokr.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val intent = intent.getStringExtra("imageURL")
        Picasso.with(image_viewer.context).load(intent)
            .fit()
            .centerCrop()
            .into(image_viewer)
    }
}
