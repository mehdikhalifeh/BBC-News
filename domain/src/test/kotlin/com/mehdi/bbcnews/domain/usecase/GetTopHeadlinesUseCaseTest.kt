package com.mehdi.bbcnews.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.domain.model.NewsArticle
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.model.NewsSource
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.result.DomainError
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTopHeadlinesUseCaseTest {

    private lateinit var getTopHeadlines: GetTopHeadlinesUseCase
    private val repository: NewsRepository = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()
    private val newsSorter = NewsSorter()

    @Test
    fun `call should emit Success when repository returns data`() = runTest {
        // Given
        val source = "bbc-news"
        val olderArticle = buildArticle(
            title = "Older",
            publishedAt = "2023-04-22T14:37:14.7569359Z"
        )
        val newerArticle = buildArticle(
            title = "Newer",
            publishedAt = "2023-04-22T15:37:19.3827616Z"
        )
        val expectedResponse = NewsResponse(
            listOf(
                olderArticle,
                newerArticle
            ),
            "ok",
            1
        )

        coEvery { repository.getTopHeadlines(source) } returns Result.Success(expectedResponse)

        getTopHeadlines = GetTopHeadlinesUseCase(repository, newsSorter, testDispatcher)

        // When
        val flow = getTopHeadlines(source)

        // Then
        flow.test {
            assertThat(Result.Loading).isEqualTo(awaitItem())
            val success = awaitItem() as Result.Success
            assertThat(success.data.articles.first().title).isEqualTo("Newer")
            assertThat(success.data.articles.last().title).isEqualTo("Older")
            awaitComplete()
        }
    }


    @Test
    fun `call should emit Failure when repository returns error`() =
        runTest {
            // Given
            val source = "bbc-news"
            val expectedError = DomainError.Network()
            coEvery { repository.getTopHeadlines(source) } returns Result.Failure(expectedError)
            getTopHeadlines = GetTopHeadlinesUseCase(repository, newsSorter, testDispatcher)

            // When
            val flow = getTopHeadlines(source)

            // Then
            flow.test {
                assertThat(Result.Loading).isEqualTo(awaitItem())
                assertThat(Result.Failure(expectedError)).isEqualTo(awaitItem())
                awaitComplete()
            }
        }

    @Test
    fun `call should emit Loading when repository returns Loading`() = runTest {
        // Given
        val source = "bbc-news"
        coEvery { repository.getTopHeadlines(source) } returns Result.Loading
        getTopHeadlines = GetTopHeadlinesUseCase(repository, newsSorter, testDispatcher)

        // When
        val flow = getTopHeadlines(source)

        // Then
        flow.test {
            assertThat(Result.Loading).isEqualTo(awaitItem())
            assertThat(Result.Loading).isEqualTo(awaitItem())
            awaitComplete()
        }
    }

    private fun buildArticle(
        title: String,
        publishedAt: String,
    ): NewsArticle {
        return NewsArticle(
            author = "BBC News",
            content = "Content",
            description = "Description",
            publishedAt = publishedAt,
            source = NewsSource(id = "bbc-news", name = "BBC News"),
            title = title,
            url = "http://www.bbc.co.uk/news/example",
            urlToImage = "https://ichef.bbci.co.uk/news/example.jpg"
        )
    }
}
