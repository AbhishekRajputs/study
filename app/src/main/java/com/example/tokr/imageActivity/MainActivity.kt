package com.example.tokr.imageActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tokr.CommonUtils.showToast
import com.example.tokr.R
import com.example.tokr.imageSlider.ImageSliderViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val imageSliderViewModel by lazy {
        ViewModelProviders.of(this).get(ImageSliderViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageSliderViewModel.getImages()
    }

    override fun onResume() {
        super.onResume()
        imageSliderViewModel.mutableLiveData.observe(this, Observer {
            recycler_view.layoutManager =
                GridLayoutManager(
                    this@MainActivity, 3,
                    GridLayoutManager.VERTICAL, false
                )
            recycler_view.adapter = Adapter(it)
        })
    }
}
