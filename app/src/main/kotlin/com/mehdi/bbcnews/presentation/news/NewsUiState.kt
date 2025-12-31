package com.mehdi.bbcnews.presentation.news

import com.mehdi.bbcnews.domain.model.Article

sealed class NewsUiState {
    data object Loading : NewsUiState()
    data class Content(val articles: List<NewsUiModel>) : NewsUiState()
    data class Error(val message: String) : NewsUiState()

    companion object {
        fun fromArticles(articles: List<Article>): NewsUiState {
            return Content(articles.map { article ->
                NewsUiModel(
                    title = article.title,
                    description = article.description.orEmpty(),
                    imageUrl = article.imageUrl,
                    url = article.url,
                    source = article.source,
                    publishedAt = article.publishedAt
                )
            })
        }
    }
}

data class NewsUiModel(
    val title: String,
    val description: String,
    val imageUrl: String?,
    val url: String?,
    val source: String?,
    val publishedAt: String?
)
