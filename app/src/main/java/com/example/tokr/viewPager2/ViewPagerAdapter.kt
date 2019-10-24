package com.example.tokr.viewPager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tokr.R
import com.example.tokr.modals.TotalHits
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_viewpager.view.*


class ViewPagerAdapter(var list: ArrayList<TotalHits.Hit>) :
    RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_viewpager,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(hit: TotalHits.Hit) {
            Picasso.with(itemView.img_show_image.context).load(hit.largeImageURL)
                .fit()
                .into(itemView.img_show_image)

        }
    }

}
