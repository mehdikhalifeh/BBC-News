package com.mehdi.bbcnews.domain.model

data class NewsResponse(
    val articles: List<NewsArticle>,
    val status: String,
    val totalResults: Int,
)
