package com.example.tokr.main

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tokr.R
import com.example.tokr.imageGridActivity.ImageGridActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpAdapter()

        adapter.setClickListener(object : MainAdapter.ButtonClickListener {
            override fun onBtnClick(position: Int) {
                when (position) {
                    0 -> {
                        val intent = Intent(this@MainActivity, ImageGridActivity::class.java)
                        startActivity(
                            intent,
                            ActivityOptions.makeSceneTransitionAnimation(this@MainActivity).toBundle()
                        )
                    }
                }
            }
        })
    }

    private fun setUpAdapter() {
        val list = arrayListOf<String>()
        list.apply {
            add("Image")
            add("Media")
        }
        adapter = MainAdapter(list)
        actions_recyclerview.layoutManager = GridLayoutManager(
            this@MainActivity, 2,
            GridLayoutManager.VERTICAL, false
        )
        val animation = AnimationUtils.loadLayoutAnimation(
            this, R.anim.layout_animation
        )
        actions_recyclerview.layoutAnimation = animation
        actions_recyclerview.adapter = adapter
    }
}
