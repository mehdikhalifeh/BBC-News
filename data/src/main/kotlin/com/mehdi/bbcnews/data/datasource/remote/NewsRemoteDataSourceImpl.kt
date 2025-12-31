package com.mehdi.bbcnews.data.datasource.remote

import com.mehdi.bbcnews.data.api.NewsApi
import com.mehdi.bbcnews.data.dto.ArticleDto

class NewsRemoteDataSourceImpl(
    private val newsApi: NewsApi,
    private val apiKey: String
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(): List<ArticleDto> {
        return newsApi.getTopHeadlines(apiKey = apiKey).articles
    }
}
