package com.mehdi.bbcnews.data.remote

import com.mehdi.bbcnews.data.model.NewsSorter
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.remote.connection.NewsListApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val newsListApi: NewsListApi, private val newsSorter: NewsSorter
) : RemoteDataSource {
    override suspend fun getTopHeadlines(source: String): BbcNewsResponse {
        val response = newsListApi.getTopHeadlines(source)
        val sortedArticles = newsSorter.sort(response.articles)
        return BbcNewsResponse(sortedArticles, response.status, response.totalResults)
    }
}
