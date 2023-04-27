package com.mehdi.bbcnews

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.RepositoryImpl
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import com.mehdi.bbcnews.data.model.responses.Source
import com.mehdi.bbcnews.data.remote.RemoteDataSourceImpl
import com.mehdi.bbcnews.data.remote.connection.NewsListApi
import com.mehdi.bbcnews.domain.NewsSorter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RepositoryAndDatasourceIntegrationTest {

    private val newsListApi = mockk<NewsListApi>(relaxed = true)
    private val newsSorter = mockk<NewsSorter>(relaxed = true)
    private val remoteDataSource = RemoteDataSourceImpl(newsListApi)
    private lateinit var repositoryImpl: RepositoryImpl

    @Before
    fun setup() {
        repositoryImpl = RepositoryImpl(remoteDataSource)
    }

    @Test
    fun `when getTopHeadlines is called with valid source, then it should return BbcNewsResponse`() =
        runTest {
            // given
            val source = "bbc-news"
            val unsortedArticles = listOf(
                Article(
                    author = "BBC News",
                    content = "More than 3,000 people have been evacuated from their homes in the Russian city of Belgorod after an undetonated explosive was found.\r\nIt comes two days after Russia accidentally dropped a bomb on th… [+1573 chars]",
                    description = "An undetonated bomb was found in Belgorod, where a jet accidentally dropped another bomb days earlier.",
                    publishedAt = "2023-04-24T17:52:17.9514134Z",
                    source = Source(id = "bbc-news", name = "BBC News"),
                    title = "Russia's Belgorod sees mass evacuations over undetonated bomb",
                    url = "http://www.bbc.co.uk/news/world-europe-65358070",
                    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/437A/production/_129447271_gettyimages-1252028554.jpg"
                ),
                Article(
                    author = "BBC News",
                    content = "A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]",
                    description = "A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.",
                    publishedAt = "2023-04-24T17:52:17.9514134Z",
                    source = Source(id = "bbc-news", name = "BBC News"),
                    title = "Dame Edna's memorable moments in 60 seconds",
                    url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
                    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg"
                )
            )
            val sortedArticles = listOf(
                unsortedArticles[1],
                unsortedArticles[0]
            )
            val response = BbcNewsResponse(
                articles = sortedArticles,
                status = "ok",
                totalResults = 2
            )
            coEvery { newsListApi.getTopHeadlines(source) } returns response
            every { newsSorter.sort(unsortedArticles) } returns sortedArticles
            // when

            val result = repositoryImpl.getTopHeadlines(source)

            // then
            coVerify(exactly = 1) { newsListApi.getTopHeadlines(source) }
            assertThat(response).isEqualTo(result)
        }
}
