package com.mehdi.bbcnews.domain.usecases

import com.mehdi.bbcnews.domain.Repository
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import com.mehdi.bbcnews.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopHeadlines @Inject constructor(
    private val repository: Repository,
    private val newsSorter: NewsSorter
) {
    suspend operator fun invoke(source: String) = flow {
        emit(DataState.Loading)
        delay(100)
        try {
            val response = repository.getTopHeadlines(source)
            val sortedArticles = newsSorter.sort(response.articles)
            val sortedResponse = response.copy(articles = sortedArticles)
            emit(DataState.Success(sortedResponse))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}