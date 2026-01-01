package com.mehdi.bbcnews.di

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.dto.ArticleDto
import com.mehdi.bbcnews.data.dto.NewsResponseDto
import com.mehdi.bbcnews.data.dto.SourceDto
import com.mehdi.bbcnews.data.remote.NewsListApi
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Test
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
class HiltComponentTest {

    @Test
    fun `hilt component wires repository and use case`() = runTest {
        val component = DaggerHiltComponentTest_TestComponent.create()

        val results = component.getTopHeadlinesUseCase().invoke("bbc-news").toList()

        assertThat(results.first()).isEqualTo(Result.Loading)
        val success = results.last() as Result.Success
        assertThat(success.data.articles).hasSize(1)
        assertThat(success.data.articles.first().title).isEqualTo("Hilt wiring")
    }

    @Singleton
    @Component(
        modules = [
            DataModule::class,
            DispatcherModule::class,
            SorterModule::class,
            UseCaseModule::class,
            TestApiModule::class,
        ]
    )
    interface TestComponent {
        fun getTopHeadlinesUseCase(): GetTopHeadlinesUseCase
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestApiModule {
        @Provides
        fun provideNewsListApi(): NewsListApi {
            return object : NewsListApi {
                override suspend fun getTopHeadlines(source: String): NewsResponseDto {
                    return NewsResponseDto(
                        articles =
                            listOf(
                                ArticleDto(
                                    author = "BBC",
                                    content = "content",
                                    description = "description",
                                    publishedAt = "2024-01-01",
                                    source = SourceDto(id = source, name = "BBC"),
                                    title = "Hilt wiring",
                                    url = "https://example.com",
                                    urlToImage = "https://example.com/image.png",
                                )
                            ),
                        status = "ok",
                        totalResults = 1,
                    )
                }
            }
        }
    }
}
