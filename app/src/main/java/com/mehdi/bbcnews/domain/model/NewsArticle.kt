package com.mehdi.bbcnews.domain.model

import java.io.Serializable

data class NewsArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: NewsSource,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable
