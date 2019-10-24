package com.example.tokr.imageSlider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.tokr.modals.TotalHits
import timber.log.Timber


class ImageSliderViewModel : ViewModel(),
    ImageSliderViewModelImpl.ImageSliderListener {

    internal var mutableLiveData: MutableLiveData<ArrayList<TotalHits.Hit>> = MutableLiveData()

    fun getImages() {
        if (mutableLiveData.value == null) {
            ImageSliderViewModelImpl().getImages(this)
            Timber.d("newdata")
        } else if (mutableLiveData.value != null) {
            val oldValue = mutableLiveData.value
            mutableLiveData.value = oldValue
            Timber.d("olddata")
        }
    }

    override fun onImagesSuccess(response: ArrayList<TotalHits.Hit>) {
        mutableLiveData.value = response

    }

    override fun onImagesFailure(errorMessage: String) {
    }


}