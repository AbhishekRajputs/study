package com.example.tokr.imageSlider

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tokr.R
import com.example.tokr.modals.TotalHits
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.activity_image_slider.*


class ImageSliderActivity : AppCompatActivity() {

    private val imageSliderViewModel by lazy {
        ViewModelProviders.of(this).get(ImageSliderViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar!!.hide()

        setContentView(R.layout.activity_image_slider)
        imageSliderViewModel.getImages()
        imageSliderViewModel.mutableLiveData.observe(this, Observer {
            setUpRecyclerView(it)
        })

    }

    private fun setUpRecyclerView(response: ArrayList<TotalHits.Hit>) {
        imageSlider.sliderAdapter = SliderAdapter(response)
        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.indicatorSelectedColor = Color.WHITE
        imageSlider.indicatorUnselectedColor = Color.GRAY
        imageSlider.startAutoCycle()

    }

}
