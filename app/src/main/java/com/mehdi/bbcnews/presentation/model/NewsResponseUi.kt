package com.mehdi.bbcnews.presentation.model

data class NewsResponseUi(
    val articles: List<NewsArticleUi>,
    val status: String,
    val totalResults: Int,
)
