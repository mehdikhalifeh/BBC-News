package com.mehdi.bbcnews.domain.model

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NewsModelsTest {

    @Test
    fun `news models should expose constructor values`() {
        val source = NewsSource(id = "bbc-news", name = "BBC News")
        val article = NewsArticle(
            author = "BBC News",
            content = "Content",
            description = "Description",
            publishedAt = "2023-04-22T14:37:14.7569359Z",
            source = source,
            title = "Title",
            url = "http://www.bbc.co.uk/news/example",
            urlToImage = "https://ichef.bbci.co.uk/news/example.jpg"
        )
        val response = NewsResponse(
            articles = listOf(article),
            status = "ok",
            totalResults = 1
        )

        assertThat(source.id).isEqualTo("bbc-news")
        assertThat(source.name).isEqualTo("BBC News")
        assertThat(article.source).isEqualTo(source)
        assertThat(article.title).isEqualTo("Title")
        assertThat(response.articles.single()).isEqualTo(article)
        assertThat(response.status).isEqualTo("ok")
        assertThat(response.totalResults).isEqualTo(1)
    }
}
