package com.mehdi.bbcnews.di

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.BuildConfig
import com.mehdi.bbcnews.data.remote.NewsListApi
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class DiModulesTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `network module provides expected instances`() {
        val okHttpClient = NetworkModule.provideHttpClient()
        val converterFactory = NetworkModule.provideConverterFactory()
        val retrofit = NetworkModule.provideRetrofitInstance(
            okHttpClient,
            converterFactory,
            "https://example.com/"
        )
        val apiService = NetworkModule.provideApiService(retrofit)

        assertThat(okHttpClient).isInstanceOf(OkHttpClient::class.java)
        assertThat(okHttpClient.interceptors).isNotEmpty()
        assertThat(converterFactory).isInstanceOf(GsonConverterFactory::class.java)
        assertThat(retrofit).isInstanceOf(Retrofit::class.java)
        assertThat(retrofit.baseUrl().toString()).isEqualTo("https://example.com/")
        assertThat(apiService).isInstanceOf(NewsListApi::class.java)
    }

    @Test
    fun `network module exposes base url`() {
        assertThat(NetworkModule.provideBaseUrl()).isEqualTo(BuildConfig.URL)
    }

    @Test
    fun `dispatcher module provides standard dispatchers`() {
        assertThat(DispatcherModule.provideDefaultDispatcher()).isSameInstanceAs(Dispatchers.Default)
        assertThat(DispatcherModule.provideIoDispatcher()).isSameInstanceAs(Dispatchers.IO)
        assertThat(DispatcherModule.provideMainDispatcher()).isSameInstanceAs(Dispatchers.Main)
    }

    @Test
    fun `sorter module provides news sorter`() {
        assertThat(SorterModule.provideNewsSorter()).isInstanceOf(NewsSorter::class.java)
    }

    @Test
    fun `use case module provides get top headlines use case`() {
        val repository = FakeNewsRepository()
        val newsSorter = NewsSorter()
        val dispatcher = Dispatchers.IO

        val useCase = UseCaseModule.provideGetTopHeadlinesUseCase(repository, newsSorter, dispatcher)

        assertThat(useCase).isInstanceOf(GetTopHeadlinesUseCase::class.java)
    }

    @Test
    fun `qualifiers are retained at runtime`() {
        assertThat(BaseUrl::class.annotations.filterIsInstance<Retention>().single().value)
            .isEqualTo(AnnotationRetention.RUNTIME)
        assertThat(IoDispatcher::class.annotations.filterIsInstance<Retention>().single().value)
            .isEqualTo(AnnotationRetention.RUNTIME)
        assertThat(DefaultDispatcher::class.annotations.filterIsInstance<Retention>().single().value)
            .isEqualTo(AnnotationRetention.RUNTIME)
        assertThat(MainDispatcher::class.annotations.filterIsInstance<Retention>().single().value)
            .isEqualTo(AnnotationRetention.RUNTIME)
    }

    private class FakeNewsRepository : NewsRepository {
        override suspend fun getTopHeadlines(source: String) =
            throw IllegalStateException("Not used in this test")
    }
}
