package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetTopHeadlinesUseCase(
        repository: NewsRepository,
        newsSorter: NewsSorter,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetTopHeadlinesUseCase {
        return GetTopHeadlinesUseCase(repository, newsSorter, dispatcher)
    }
}
