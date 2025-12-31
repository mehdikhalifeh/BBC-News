package com.mehdi.bbcnews.data.remote

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.dto.ArticleDto
import com.mehdi.bbcnews.data.dto.NewsResponseDto
import com.mehdi.bbcnews.data.dto.SourceDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsRemoteDataSourceImplTest {

    private val newsListApi: NewsListApi = mockk()

    private val remoteDataSource = NewsRemoteDataSourceImpl(newsListApi)

    @Test
    fun `getTopHeadlines returns sorted articles`() = runTest {
        val response = NewsResponseDto(
            listOf(
                ArticleDto(
                    author = "BBC News",
                    content = "A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]",
                    description = "A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.",
                    publishedAt = "2023-04-22T15:37:19.3827616Z",
                    source = SourceDto(id = "bbc-news", name = "BBC News"),
                    title = "Dame Edna's memorable moments in 60 seconds",
                    url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
                    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg"
                ),
                ArticleDto(
                    author = "BBC News",
                    content = "More than 3,000 people have been evacuated from their homes in the Russian city of Belgorod after an undetonated explosive was found.\r\nIt comes two days after Russia accidentally dropped a bomb on th… [+1573 chars]",
                    description = "An undetonated bomb was found in Belgorod, where a jet accidentally dropped another bomb days earlier.",
                    publishedAt = "2023-04-22T14:37:14.7569359Z",
                    source = SourceDto(id = "bbc-news", name = "BBC News"),
                    title = "Russia's Belgorod sees mass evacuations over undetonated bomb",
                    url = "http://www.bbc.co.uk/news/world-europe-65358070",
                    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/437A/production/_129447271_gettyimages-1252028554.jpg"
                ),
            ),
            status = "ok",
            totalResults = 2
        )

        coEvery { newsListApi.getTopHeadlines(any()) } returns response

        val result = remoteDataSource.getTopHeadlines("bbc-news")

        assertThat(response).isEqualTo(result)
    }
}
