package com.mehdi.bbcnews.data.repository

import com.mehdi.bbcnews.data.datasource.remote.NewsRemoteDataSource
import com.mehdi.bbcnews.data.error.DomainErrorMapper
import com.mehdi.bbcnews.data.mapper.ArticleMapper
import com.mehdi.bbcnews.domain.model.Article
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.result.Result

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val mapper: ArticleMapper,
    private val errorMapper: DomainErrorMapper
) : NewsRepository {
    override suspend fun fetchTopHeadlines(): Result<List<Article>> {
        return try {
            val articles = remoteDataSource.getTopHeadlines()
            Result.Success(articles.map(mapper::toDomain))
        } catch (throwable: Throwable) {
            Result.Failure(errorMapper.map(throwable))
        }
    }
}
