package com.mehdi.bbcnews.component

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.model.responses.Source
import com.mehdi.bbcnews.domain.usecases.GetTopHeadlines
import com.mehdi.bbcnews.util.DataState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {

    private lateinit var viewModel: NewsListViewModel
    private val getTopHeadlines: GetTopHeadlines = mockk()


    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = NewsListViewModel(getTopHeadlines)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTopHeadlines should update topHeadlines with DataState Success when getTopHeadlines returns data`() =
        runTest {
            // Given
            val source = "bbc-news"
            val expectedResponse = BbcNewsResponse(
                listOf(
                    Article(
                        author = "BBC News",
                        content = "A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]",
                        description = "A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.",
                        publishedAt = "2023-04-22T15:37:19.3827616Z",
                        source = Source(id = "bbc-news", name = "BBC News"),
                        title = "Dame Edna's memorable moments in 60 seconds",
                        url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
                        urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg"
                    )
                ), "ok", 1
            )
            coEvery { getTopHeadlines(source) } returns flowOf(
                DataState.Success(
                    expectedResponse
                )
            )

            // When
            viewModel.getTopHeadlines()
            advanceTimeBy(500)

            // Then
            assertThat(viewModel.topHeadlines.value).isEqualTo(DataState.Success(expectedResponse))
        }

    @Test
    fun `getTopHeadlines should update topHeadlines with DataState Error when getTopHeadlines returns error`() =
        runTest {
            // Given
            val source = "bbc-news"
            val expectedException = Exception()
            coEvery { getTopHeadlines(source) } returns flowOf(
                DataState.Error(
                    expectedException
                )
            )

            // When
            viewModel.getTopHeadlines()
            advanceTimeBy(500)

            // Then
            assertThat(viewModel.topHeadlines.value).isEqualTo(DataState.Error(expectedException))

        }
}

