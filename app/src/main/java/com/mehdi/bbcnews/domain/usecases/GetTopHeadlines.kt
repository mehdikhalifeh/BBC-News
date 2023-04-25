package com.mehdi.bbcnews.domain.usecases

import com.mehdi.bbcnews.domain.Repository
import com.mehdi.bbcnews.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTopHeadlines @Inject constructor(
    private val repository: Repository
) {
    suspend fun call(source: String) = flow {
        emit(DataState.Loading)
        delay(100)
        try {
            emit(DataState.Success(repository.getTopHeadlines(source)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}