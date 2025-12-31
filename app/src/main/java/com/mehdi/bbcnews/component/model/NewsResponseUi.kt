package com.mehdi.bbcnews.component.model

data class NewsResponseUi(
    val articles: List<NewsArticleUi>,
    val status: String,
    val totalResults: Int,
)
