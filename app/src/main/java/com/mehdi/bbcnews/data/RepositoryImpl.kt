package com.mehdi.bbcnews.data

import com.mehdi.bbcnews.data.remote.RemoteDataSource
import com.mehdi.bbcnews.data.model.mapper.toDomain
import com.mehdi.bbcnews.domain.Repository
import com.mehdi.bbcnews.domain.model.NewsResponse
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getTopHeadlines(source: String): NewsResponse {
        return remoteDataSource.getTopHeadlines(source).toDomain()
    }
}
