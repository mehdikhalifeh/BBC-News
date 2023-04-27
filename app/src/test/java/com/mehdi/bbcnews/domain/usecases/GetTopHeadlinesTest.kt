package com.mehdi.bbcnews.domain.usecases

import app.cash.turbine.testIn
import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.model.responses.Source
import com.mehdi.bbcnews.domain.NewsSorter
import com.mehdi.bbcnews.domain.Repository
import com.mehdi.bbcnews.util.DataState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetTopHeadlinesTest {

    private lateinit var getTopHeadlines: GetTopHeadlines
    private val repository: Repository = mockk()


    @Test
    fun `call should emit DataState Success when repository returns data`() = runTest {
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
            ),
            "ok",
            1
        )

        coEvery { repository.getTopHeadlines(source) } returns expectedResponse

        val newsSorter = NewsSorter()
        newsSorter.sort(expectedResponse.articles)
        getTopHeadlines = GetTopHeadlines(repository, newsSorter)

        // When
        val flow = getTopHeadlines.call(source)

        // Then
        val turbine = flow.testIn(this)
        assertThat(DataState.Loading).isEqualTo(turbine.awaitItem())
        delay(100)
        assertThat(DataState.Success(expectedResponse)).isEqualTo(turbine.awaitItem())
        turbine.awaitComplete()
    }


    @Test
    fun `call should emit DataState Error when repository throws exception`() =
        runTest {
            // Given
            val source = "bbc-news"
            val expectedException = Exception()
            coEvery { repository.getTopHeadlines(source) } throws expectedException
            val newsSorter = mockk<NewsSorter>()
            getTopHeadlines = GetTopHeadlines(repository, newsSorter)

            // When
            val flow = getTopHeadlines.call(source)

            // Then
            val turbine = flow.testIn(this)
            assertThat(DataState.Loading).isEqualTo(turbine.awaitItem())
            delay(100)
            assertThat(DataState.Error(expectedException)).isEqualTo(turbine.awaitItem())
            turbine.awaitComplete()
        }
}
