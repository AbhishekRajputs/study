package com.example.tokr.imageActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.crashlytics.android.Crashlytics
import com.example.tokr.R
import com.example.tokr.imageSlider.ImageSliderActivity
import com.example.tokr.imageSlider.ImageSliderViewModel
import com.example.tokr.viewPager2.ViewPagerActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val imageSliderViewModel by lazy {
        ViewModelProviders.of(this).get(ImageSliderViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageSliderViewModel.getImages()
//        Crashlytics.getInstance().crash()

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

    override fun onResume() {
        super.onResume()
        imageSliderViewModel.mutableLiveData.observe(this, Observer {
            recycler_view.layoutManager =
                GridLayoutManager(
                    this@MainActivity, 3,
                    GridLayoutManager.VERTICAL, false
                )
            recycler_view.adapter = Adapter(it)
        })
    }
}
