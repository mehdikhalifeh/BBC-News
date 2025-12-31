package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.data.remote.NewsRemoteDataSource
import com.mehdi.bbcnews.data.remote.NewsRemoteDataSourceImpl
import com.mehdi.bbcnews.data.repository.NewsRepositoryImpl
import com.mehdi.bbcnews.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(
        impl: NewsRemoteDataSourceImpl
    ): NewsRemoteDataSource
}
