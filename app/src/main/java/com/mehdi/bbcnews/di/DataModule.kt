package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.data.remote.NewsListApi
import com.mehdi.bbcnews.data.remote.NewsRemoteDataSource
import com.mehdi.bbcnews.data.remote.NewsRemoteDataSourceImpl
import com.mehdi.bbcnews.data.repository.NewsRepositoryImpl
import com.mehdi.bbcnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSourceImpl: NewsRemoteDataSourceImpl,
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataSourceImpl)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        newsListApi: NewsListApi
    ): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsListApi)
    }
}
