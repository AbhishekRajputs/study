package com.example.tokr

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.IndicatorAnimations
import kotlinx.android.synthetic.main.activity_image_slider.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ImageSliderActivity : Activity() {

    private val API_KEY = "14023284-d99051f4d9d5bcdc87257ad14"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_slider)

        val service =
            RetrofitClientInstance.retrofitInstance!!.create(Services::class.java)

        val call = service.getAllPhotos(API_KEY, "photo")
        call.enqueue(object : Callback<TotalHits> {
            override fun onResponse(call: Call<TotalHits>, response: Response<TotalHits>) {
                val body = response.body()


                imageSlider.sliderAdapter = SliderAdapter(body!!.hits)

                imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
                imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
                imageSlider.indicatorSelectedColor = Color.WHITE
                imageSlider.indicatorUnselectedColor = Color.GRAY
                imageSlider.startAutoCycle()
            }

            override fun onFailure(call: Call<TotalHits>, t: Throwable) {
                Toast.makeText(
                    this@ImageSliderActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })




    }
}
