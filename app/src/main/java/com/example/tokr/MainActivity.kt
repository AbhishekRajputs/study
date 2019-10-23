package com.example.tokr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val API_KEY = "14023284-d99051f4d9d5bcdc87257ad14"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service =
            RetrofitClientInstance.retrofitInstance!!.create(Services::class.java)

        val call = service.getAllPhotos(API_KEY, "photo")
        call.enqueue(object : Callback<TotalHits> {
            override fun onResponse(call: Call<TotalHits>, response: Response<TotalHits>) {
                val body = response.body()
                recycler_view.layoutManager =
                    GridLayoutManager(this@MainActivity, 3, GridLayoutManager.VERTICAL, false)
                recycler_view.adapter = Adapter(body!!.hits)

            }

            override fun onFailure(call: Call<TotalHits>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
