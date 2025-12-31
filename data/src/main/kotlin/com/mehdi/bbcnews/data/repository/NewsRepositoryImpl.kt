package com.mehdi.bbcnews.data.repository

import com.mehdi.bbcnews.data.mapper.toDomain
import com.mehdi.bbcnews.data.remote.NewsRemoteDataSource
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.result.DomainError
import com.mehdi.bbcnews.domain.result.Result
import java.io.IOException
import javax.inject.Inject
import retrofit2.HttpException

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
) : NewsRepository {
    override suspend fun getTopHeadlines(source: String): Result<NewsResponse> {
        return try {
            val response = remoteDataSource.getTopHeadlines(source).toDomain()
            Result.Success(response)
        } catch (exception: HttpException) {
            Result.Failure(DomainError.Http(exception.code(), exception.message()))
        } catch (exception: IOException) {
            Result.Failure(DomainError.Network(exception))
        } catch (exception: Throwable) {
            Result.Failure(DomainError.Unknown(exception))
        }
    }
}
