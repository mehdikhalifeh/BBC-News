package com.mehdi.bbcnews.data.model.mapper

import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.model.responses.Source
import com.mehdi.bbcnews.domain.model.NewsArticle
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.model.NewsSource

fun BbcNewsResponse.toDomain(): NewsResponse = NewsResponse(
    status = status,
    totalResults = totalResults,
    articles = articles.map { it.toDomain() }
)

fun Article.toDomain(): NewsArticle = NewsArticle(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source.toDomain(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun Source.toDomain(): NewsSource = NewsSource(
    id = id,
    name = name
)
