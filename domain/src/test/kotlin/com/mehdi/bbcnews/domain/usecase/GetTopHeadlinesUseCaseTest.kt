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
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTopHeadlinesUseCaseTest {

    private lateinit var getTopHeadlines: GetTopHeadlinesUseCase
    private val repository: NewsRepository = mockk()


    @Test
    fun `call should emit Success when repository returns data`() = runTest {
        // Given
        val source = "bbc-news"
        val expectedResponse = NewsResponse(
            listOf(
                NewsArticle(
                    author = "BBC News",
                    content = "A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]",
                    description = "A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.",
                    publishedAt = "2023-04-22T15:37:19.3827616Z",
                    source = NewsSource(id = "bbc-news", name = "BBC News"),
                    title = "Dame Edna's memorable moments in 60 seconds",
                    url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
                    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg"
                )
            ),
            "ok",
            1
        )

        coEvery { repository.getTopHeadlines(source) } returns Result.Success(expectedResponse)

        val newsSorter = NewsSorter()
        getTopHeadlines = GetTopHeadlinesUseCase(repository, newsSorter)

        // When
        val flow = getTopHeadlines(source)

        // Then
        flow.test {
            assertThat(Result.Loading).isEqualTo(awaitItem())
            assertThat(Result.Success(expectedResponse)).isEqualTo(awaitItem())
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
            val newsSorter = NewsSorter()
            getTopHeadlines = GetTopHeadlinesUseCase(repository, newsSorter)

            // When
            val flow = getTopHeadlines(source)

            // Then
            flow.test {
                assertThat(Result.Loading).isEqualTo(awaitItem())
                assertThat(Result.Failure(expectedError)).isEqualTo(awaitItem())
                awaitComplete()
            }
        }
}
