package com.mehdi.bbcnews.data.model.responses


import com.google.gson.annotations.SerializedName

data class BbcNewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)