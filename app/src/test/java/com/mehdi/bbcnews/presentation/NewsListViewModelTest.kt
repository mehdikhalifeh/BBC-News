package com.mehdi.bbcnews.presentation

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.presentation.model.NewsArticleUi
import com.mehdi.bbcnews.presentation.model.NewsResponseUi
import com.mehdi.bbcnews.presentation.model.NewsSourceUi
import com.mehdi.bbcnews.presentation.state.NewsListUiState
import com.mehdi.bbcnews.domain.model.NewsArticle
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.model.NewsSource
import com.mehdi.bbcnews.domain.result.DomainError
import com.mehdi.bbcnews.domain.result.Result
import com.mehdi.bbcnews.domain.usecase.GetTopHeadlinesUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {

    private lateinit var viewModel: NewsListViewModel
    private val getTopHeadlines: GetTopHeadlinesUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getTopHeadlines("bbc-news") } returns flowOf(Result.Loading) // Default fallback
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `loadTopHeadlines updates state with Content when use case returns data`() =
        runTest(testDispatcher) {
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
                ), "ok", 1
            )
            coEvery { getTopHeadlines(source) } returns flowOf(
                Result.Success(
                    expectedResponse
                )
            )
            viewModel = NewsListViewModel(getTopHeadlines)

            // When
            viewModel.loadTopHeadlines()
            advanceUntilIdle()

            // Then
            assertThat(viewModel.topHeadlines.value).isEqualTo(
                NewsListUiState.Content(
                    NewsResponseUi(
                        articles = listOf(
                            NewsArticleUi(
                                author = "BBC News",
                                content = "A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]",
                                description = "A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.",
                                publishedAt = "2023-04-22T15:37:19.3827616Z",
                                source = NewsSourceUi(id = "bbc-news", name = "BBC News"),
                                title = "Dame Edna's memorable moments in 60 seconds",
                                url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
                                urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg",
                            )
                        ),
                        status = "ok",
                        totalResults = 1,
                    )
                )
            )
        }

    @Test
    fun `loadTopHeadlines updates state with Error when use case returns failure`() =
        runTest(testDispatcher) {
            // Given
            val source = "bbc-news"
            val expectedError = DomainError.Network()
            coEvery { getTopHeadlines(source) } returns flowOf(
                Result.Failure(expectedError)
            )
            viewModel = NewsListViewModel(getTopHeadlines)

            // When
            viewModel.loadTopHeadlines()
            advanceUntilIdle()

            // Then
            assertThat(viewModel.topHeadlines.value).isEqualTo(NewsListUiState.Error(expectedError))
        }

    @Test
    fun `loadTopHeadlines keeps Loading state when use case emits loading`() =
        runTest(testDispatcher) {
            // Given
            coEvery { getTopHeadlines("bbc-news") } returns flowOf(Result.Loading)
            viewModel = NewsListViewModel(getTopHeadlines)

            // When
            advanceUntilIdle()

            // Then
            assertThat(viewModel.topHeadlines.value).isEqualTo(NewsListUiState.Loading)
        }

    @Test
    fun `onRefresh ignores repeated refresh requests while refreshing`() =
        runTest(testDispatcher) {
            // Given
            val sharedFlow = MutableSharedFlow<Result<NewsResponse>>(replay = 1)
            coEvery { getTopHeadlines("bbc-news") } returns flow {
                emit(Result.Loading)
                sharedFlow.collect { emit(it) }
            }
            viewModel = NewsListViewModel(getTopHeadlines)
            runCurrent()

            // When
            viewModel.onRefresh()
            viewModel.onRefresh()
            runCurrent()

            // Then
            coVerify(exactly = 2) { getTopHeadlines("bbc-news") }
        }
}
