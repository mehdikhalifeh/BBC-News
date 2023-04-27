package com.mehdi.bbcnews.data.remote

import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.remote.connection.NewsListApi
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val newsListApi: NewsListApi
) : RemoteDataSource {
    override suspend fun getTopHeadlines(source: String): BbcNewsResponse {
        return newsListApi.getTopHeadlines(source)
    }
}
