package com.example.tokr.imageSlider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.tokr.R
import com.example.tokr.modals.TotalHits
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso


class SliderAdapter(var list: ArrayList<TotalHits.Hit>) :
    SliderViewAdapter<SliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent!!.context).inflate(
                R.layout.image_slider_item_view,
                parent,
                false
            )
        )
    }

    override fun getCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.with(holder.imageViewBackground.context).load(list[position].largeImageURL)
            .fit()
            .into(holder.imageViewBackground)
    }


    inner class ViewHolder(itemview: View) : SliderViewAdapter.ViewHolder(itemview) {
        var imageViewBackground: ImageView = itemview.findViewById(R.id.iv_auto_image_slider)
    }
}