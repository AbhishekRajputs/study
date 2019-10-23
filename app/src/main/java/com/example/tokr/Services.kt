package com.example.tokr

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("/api/")
    fun getAllPhotos(
        @Query("key") apiKey: String
        , @Query("image_type") imageType: String
    ): Call<TotalHits>

    @GET("/api/")
    fun getPhoto(
        @Query("key") apiKey: String
        , @Query("id") imageId: String
        , @Query("image_type") imageType: String
    ): Call<TotalHits>
}