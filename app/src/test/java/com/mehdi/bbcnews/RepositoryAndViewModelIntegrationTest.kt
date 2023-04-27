package com.mehdi.bbcnews

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.model.responses.Source
import com.mehdi.bbcnews.domain.Repository
import com.mehdi.bbcnews.domain.sorter.NewsSorter
import com.mehdi.bbcnews.domain.usecases.GetTopHeadlines
import com.mehdi.bbcnews.util.DataState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi

class RepositoryAndViewModelIntegrationTest {

    private val newsSorter = mockk<NewsSorter>(relaxed = true)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetTopHeadlines() = runTest {
        val mockResponse = BbcNewsResponse(
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
            ),
            "ok",
            1
        )

        val mockRepository = mockk<Repository> {
            coEvery { getTopHeadlines(any()) } returns mockResponse
        }

        val viewModel = NewsListViewModel(GetTopHeadlines(mockRepository, newsSorter))
        val source = "bbc-news"

        val job = launch {
            viewModel.topHeadlines.collect {
                when (it) {
                    is DataState.Loading -> {
                        assertThat(mockResponse).isEqualTo(it)
                    }

                    is DataState.Success -> {
                        assertThat(mockResponse).isEqualTo(it.data)
                    }

                    is DataState.Error -> {
                        fail("Expected state to be success but was ${it.javaClass.simpleName}")
                    }
                }
            }
        }
        viewModel.getTopHeadlines(source)
        job.cancel()
    }

}