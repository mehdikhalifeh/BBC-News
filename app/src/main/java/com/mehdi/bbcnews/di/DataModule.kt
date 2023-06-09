package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.data.RepositoryImpl
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import com.mehdi.bbcnews.data.remote.RemoteDataSource
import com.mehdi.bbcnews.data.remote.RemoteDataSourceImpl
import com.mehdi.bbcnews.data.remote.connection.NewsListApi
import com.mehdi.bbcnews.domain.Repository
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
        remoteDataSourceImpl: RemoteDataSourceImpl,
    ): Repository {
        return RepositoryImpl(remoteDataSourceImpl)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        newsListApi: NewsListApi
    ): RemoteDataSource {
        return RemoteDataSourceImpl(newsListApi)
    }
}