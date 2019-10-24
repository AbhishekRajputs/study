package com.example.tokr.viewPager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tokr.CommonUtils.showFullScreen
import com.example.tokr.R
import com.example.tokr.imageSlider.ImageSliderViewModel
import kotlinx.android.synthetic.main.activity_view_pager.*


class ViewPagerActivity : AppCompatActivity() {

    private val imageSliderViewModel by lazy {
        ViewModelProviders.of(this).get(ImageSliderViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showFullScreen(window,supportActionBar!!)
        setContentView(R.layout.activity_view_pager)
        imageSliderViewModel.getImages()

        imageSliderViewModel.mutableLiveData.observe(this, Observer {
            viewPager2.adapter = ViewPagerAdapter(it)
        })
    }
}
