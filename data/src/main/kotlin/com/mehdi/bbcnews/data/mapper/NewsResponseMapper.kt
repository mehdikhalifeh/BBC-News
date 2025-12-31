package com.mehdi.bbcnews.data.mapper

import com.mehdi.bbcnews.data.dto.ArticleDto
import com.mehdi.bbcnews.data.dto.NewsResponseDto
import com.mehdi.bbcnews.data.dto.SourceDto
import com.mehdi.bbcnews.domain.model.NewsArticle
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.model.NewsSource

fun NewsResponseDto.toDomain(): NewsResponse {
    return NewsResponse(
        articles = articles.orEmpty().map { it.toDomain() },
        status = status.orEmpty(),
        totalResults = totalResults ?: 0,
    )
}

private fun ArticleDto.toDomain(): NewsArticle {
    return NewsArticle(
        author = author.orEmpty(),
        content = content.orEmpty(),
        description = description.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        source = source.toDomain(),
        title = title.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty(),
    )
}

private fun SourceDto?.toDomain(): NewsSource {
    return NewsSource(
        id = this?.id.orEmpty(),
        name = this?.name.orEmpty(),
    )
}
