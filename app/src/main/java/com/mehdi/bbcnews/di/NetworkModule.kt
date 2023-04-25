package com.mehdi.bbcnews.di

import com.mehdi.bbcnews.data.remote.connection.NewsListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.mehdi.bbcnews.BuildConfig
import com.mehdi.bbcnews.data.model.NewsSorter

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS).build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.URL

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        @BaseUrl baseUrl: String
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NewsListApi {
        return retrofit.create(NewsListApi::class.java)
    }
}