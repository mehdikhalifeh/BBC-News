package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.BuildConfig
import com.mehdi.bbcnews.data.api.NewsApi
import com.mehdi.bbcnews.data.datasource.remote.NewsRemoteDataSource
import com.mehdi.bbcnews.data.datasource.remote.NewsRemoteDataSourceImpl
import com.mehdi.bbcnews.data.error.DomainErrorMapper
import com.mehdi.bbcnews.data.mapper.ArticleMapper
import com.mehdi.bbcnews.data.repository.NewsRepositoryImpl
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsApi: NewsApi): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsApi, BuildConfig.API_KEY)
    }

    @Provides
    fun provideArticleMapper(): ArticleMapper = ArticleMapper()

    @Provides
    fun provideDomainErrorMapper(): DomainErrorMapper = DomainErrorMapper()

    @Provides
    @Singleton
    fun provideNewsRepository(
        remoteDataSource: NewsRemoteDataSource,
        mapper: ArticleMapper,
        errorMapper: DomainErrorMapper
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource, mapper, errorMapper)
    }

    @Provides
    fun provideGetTopHeadlinesUseCase(repository: NewsRepository): GetTopHeadlinesUseCase {
        return GetTopHeadlinesUseCase(repository)
    }
}
