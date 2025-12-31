package com.mehdi.bbcnews.component.model

import com.mehdi.bbcnews.domain.model.NewsArticle
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.model.NewsSource

fun NewsResponse.toUi(): NewsResponseUi {
    return NewsResponseUi(
        articles = articles.map { it.toUi() },
        status = status,
        totalResults = totalResults,
    )
}

private fun NewsArticle.toUi(): NewsArticleUi {
    return NewsArticleUi(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.toUi(),
        title = title,
        url = url,
        urlToImage = urlToImage,
    )
}

private fun NewsSource.toUi(): NewsSourceUi {
    return NewsSourceUi(
        id = id,
        name = name,
    )
}
