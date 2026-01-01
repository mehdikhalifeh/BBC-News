package com.mehdi.bbcnews.domain.usecase

import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetTopHeadlinesUseCase(
    private val repository: NewsRepository,
    private val newsSorter: NewsSorter,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(source: String): Flow<Result<NewsResponse>> = flow {
        emit(Result.Loading)
        when (val result = repository.getTopHeadlines(source)) {
            is Result.Success -> {
                val sortedArticles = newsSorter.sort(result.data.articles)
                emit(Result.Success(result.data.copy(articles = sortedArticles)))
            }

            is Result.Failure -> emit(result)
            Result.Loading -> emit(Result.Loading)
        }
    }.flowOn(dispatcher)
}
