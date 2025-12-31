package com.mehdi.bbcnews.data.remote

import com.mehdi.bbcnews.data.dto.NewsResponseDto
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val newsListApi: NewsListApi,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(source: String): NewsResponseDto {
        return newsListApi.getTopHeadlines(source)
    }
}
