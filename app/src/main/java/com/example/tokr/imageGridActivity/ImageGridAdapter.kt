package com.example.tokr.imageGridActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tokr.R
import com.example.tokr.modals.TotalHits
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class ImageGridAdapter(private var list: ArrayList<TotalHits.Hit>) :
    RecyclerView.Adapter<ImageGridAdapter.MyViewHolder>() {

    private lateinit var imageClickListener: ImageClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        )
    }

    fun setImageClickListener(imageClickListener: ImageClickListener) {
        this.imageClickListener = imageClickListener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(hit: TotalHits.Hit) {
            Picasso.with(itemView.img_image.context).load(hit.previewURL)
                .fit()
                .centerCrop()
                .into(itemView.img_image)
            itemView.img_image.setOnClickListener {
                imageClickListener.imageClick(hit.largeImageURL)
            }
        }

    }


    interface ImageClickListener {
        fun imageClick(imageURL: String)
    }
}