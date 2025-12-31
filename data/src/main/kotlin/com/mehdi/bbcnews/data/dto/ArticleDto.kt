package com.mehdi.bbcnews.data.dto

import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("urlToImage")
    val imageUrl: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("source")
    val source: SourceDto?,
    @SerializedName("publishedAt")
    val publishedAt: String?
)
