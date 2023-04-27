package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.domain.NewsSorter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SorterModule {
    @Provides
    fun provideNewsSorter(): NewsSorter {
        return NewsSorter()
    }
}