package com.example.tokr.services

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {

    private var mTokrService: Services? = null

    private const val BASE_URL = "https://pixabay.com"

    fun getInstance(): Services? {
        if (mTokrService == null) {
            val okHttpClient = makeOkHttpClient()
            val client = makeRetrofit(okHttpClient)
            mTokrService = client.create<Services>(
                Services::class.java)
        }
        return mTokrService
    }

    private fun makeRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .connectTimeout(5, TimeUnit.MINUTES)
            .cache(null)
            .build()
    }
}
