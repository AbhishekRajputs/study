package com.example.tokr.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tokr.R
import kotlinx.android.synthetic.main.rv_item_view.view.*

class MainAdapter(private var list: ArrayList<String>) :
    RecyclerView.Adapter<MainAdapter.ActionViewHolder>() {

    private lateinit var listener: ButtonClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        return ActionViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_view, parent, false)
        )
    }

    fun setClickListener(listener: ButtonClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        holder.bindItems(list[position], position)
    }


    inner class ActionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(text: String, position: Int) {
            itemView.btn_action.text = text
            itemView.btn_action.setOnClickListener { listener.onBtnClick(position) }
        }
    }

    interface ButtonClickListener {
        fun onBtnClick(position: Int)
    }
}
