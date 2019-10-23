package com.example.tokr


import com.google.gson.annotations.SerializedName


data class TotalHits(
    @SerializedName("totalHits")
    var totalHits: Int,
    @SerializedName("hits")
    var hits: ArrayList<Hit>,
    @SerializedName("total")
    var total: Int
) {
    data class Hit(
        @SerializedName("largeImageURL")
        var largeImageURL: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("type")
        var type: String,
        @SerializedName("previewURL")
        var previewURL: String
    )


}