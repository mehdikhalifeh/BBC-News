package com.mehdi.bbcnews.domain.model

data class Article(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String?
)
