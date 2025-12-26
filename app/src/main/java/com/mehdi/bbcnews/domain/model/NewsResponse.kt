package com.mehdi.bbcnews.domain.model

import java.io.Serializable

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsArticle>
) : Serializable
