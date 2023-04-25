package com.mehdi.bbcnews.data

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.model.responses.Source
import com.mehdi.bbcnews.data.remote.RemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RepositoryImplTest {

    private val mockRemoteDataSource: RemoteDataSource = mockk()
    private val repository = RepositoryImpl(mockRemoteDataSource)

    @Test
    fun `getTopHeadlines returns expected result`() = runBlocking {
        val source = "bbc-news"
        val response = BbcNewsResponse(
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
        coEvery { mockRemoteDataSource.getTopHeadlines(source) } returns response

        val result = repository.getTopHeadlines(source)

        assertThat(response).isEqualTo(result)
        coVerify { mockRemoteDataSource.getTopHeadlines(source) }
    }

    @Test
    fun `getTopHeadlines returns empty response`() = runBlocking {
        val response = BbcNewsResponse(emptyList(), "ok", 0)

        assertThat(response.articles).isEmpty()
        assertThat(response.status).isEqualTo("ok")
        assertThat(response.totalResults).isEqualTo(0)
    }
}
