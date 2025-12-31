package com.mehdi.bbcnews.domain.usecase

import com.mehdi.bbcnews.domain.repository.NewsRepository

class GetTopHeadlinesUseCase(
    private val repository: NewsRepository
) {
    suspend operator fun invoke() = repository.fetchTopHeadlines()
}
