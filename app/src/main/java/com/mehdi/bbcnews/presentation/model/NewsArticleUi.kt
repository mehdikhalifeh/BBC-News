package com.mehdi.bbcnews.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArticleUi(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: NewsSourceUi,
    val title: String,
    val url: String,
    val urlToImage: String,
) : Parcelable
