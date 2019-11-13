package com.example.tokr.imageGridActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tokr.R
import com.example.tokr.imageSlider.ImageSliderActivity
import com.example.tokr.imageSlider.ImageSliderViewModel
import com.example.tokr.viewPager2.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_image_grid.*
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tokr.CommonUtils.explodeAnimation
import com.example.tokr.imageActivity.ImageActivity


class ImageGridActivity : AppCompatActivity() {

    private lateinit var imageGridAdapter: ImageGridAdapter

    private val imageSliderViewModel by lazy {
        ViewModelProviders.of(this).get(ImageSliderViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        explodeAnimation(window)
        setContentView(R.layout.activity_image_grid)
        imageSliderViewModel.getImages()

        imageSliderViewModel.mutableLiveData.observe(this, Observer {
            imageGridAdapter = ImageGridAdapter(it)
            setUpAdapter()

            imageGridAdapter.setImageClickListener(object : ImageGridAdapter.ImageClickListener {
                override fun imageClick(imageURL: String) {

                    val intent = Intent(this@ImageGridActivity, ImageActivity::class.java)
                    intent.putExtra("imageURL", imageURL)
                    startActivity(intent)
                }
            })
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.image_slider -> startActivity(Intent(this, ImageSliderActivity::class.java))
            R.id.view_pager -> startActivity(Intent(this, ViewPagerActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpAdapter() {
        val layoutManager = GridLayoutManager(
            this@ImageGridActivity, 3,
            GridLayoutManager.VERTICAL, false
        )
        recycler_view.layoutManager = layoutManager
        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation)
        recycler_view.layoutAnimation = animation
        recycler_view.adapter = imageGridAdapter

        // this is used to disable the pull to refreshview when first item is not visible
        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                pull_to_refresh.isEnabled =
                    layoutManager.findFirstCompletelyVisibleItemPosition() === 0
            }
        })

        if (pull_to_refresh.isEnabled)
            pull_to_refresh.setOnRefreshListener {
                pull_to_refresh.postDelayed(
                    { pull_to_refresh.setRefreshing(false) },
                    4000
                )
            }
    }
}

