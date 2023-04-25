package com.mehdi.bbcnews.data

import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.remote.RemoteDataSource
import com.mehdi.bbcnews.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getTopHeadlines(source: String): BbcNewsResponse {
        return remoteDataSource.getTopHeadlines(source)
    }
}
