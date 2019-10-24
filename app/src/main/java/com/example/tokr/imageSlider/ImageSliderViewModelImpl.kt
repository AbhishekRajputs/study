package com.example.tokr.imageSlider

import com.example.tokr.services.RetrofitClientInstance
import com.example.tokr.modals.TotalHits
import kotlinx.coroutines.*

class ImageSliderViewModelImpl {

    private var job: Job? = null

    fun getImages(listener: ImageSliderListener) {
        job = CoroutineScope(Dispatchers.IO).async {
            val call = RetrofitClientInstance.getInstance()!!.getAllPhotos(
                "14023284-d99051f4d9d5bcdc87257ad14", "photo"
            ).await()
            withContext(Dispatchers.Main)
            {
                try {
                    listener.onImagesSuccess(call.hits)
                } catch (it: Throwable) {
                    it.printStackTrace()
                }
            }
        }
    }

    interface ImageSliderListener {
        fun onImagesSuccess(response: ArrayList<TotalHits.Hit>)
        fun onImagesFailure(errorMessage: String)
    }
}


