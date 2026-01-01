package com.mehdi.bbcnews.data.mapper

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.dto.ArticleDto
import com.mehdi.bbcnews.data.dto.NewsResponseDto
import com.mehdi.bbcnews.data.dto.SourceDto
import org.junit.Test

class NewsResponseMapperTest {

    @Test
    fun `toDomain maps response with populated fields`() {
        val dto = NewsResponseDto(
            articles = listOf(
                ArticleDto(
                    author = "BBC News",
                    content = "content",
                    description = "description",
                    publishedAt = "2023-04-22T15:37:19.3827616Z",
                    source = SourceDto(id = "bbc-news", name = "BBC News"),
                    title = "headline",
                    url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
                    urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/example.jpg"
                )
            ),
            status = "ok",
            totalResults = 1
        )

        val result = dto.toDomain()

        assertThat(result.status).isEqualTo("ok")
        assertThat(result.totalResults).isEqualTo(1)
        assertThat(result.articles).hasSize(1)
        val article = result.articles.first()
        assertThat(article.author).isEqualTo("BBC News")
        assertThat(article.content).isEqualTo("content")
        assertThat(article.description).isEqualTo("description")
        assertThat(article.publishedAt).isEqualTo("2023-04-22T15:37:19.3827616Z")
        assertThat(article.title).isEqualTo("headline")
        assertThat(article.url).isEqualTo("http://www.bbc.co.uk/news/entertainment-arts-65358301")
        assertThat(article.urlToImage).isEqualTo("https://ichef.bbci.co.uk/news/1024/branded_news/example.jpg")
        assertThat(article.source.id).isEqualTo("bbc-news")
        assertThat(article.source.name).isEqualTo("BBC News")
    }

    @Test
    fun `toDomain defaults missing fields to empty values`() {
        val dto = NewsResponseDto(
            articles = listOf(
                ArticleDto(
                    author = null,
                    content = null,
                    description = null,
                    publishedAt = null,
                    source = null,
                    title = null,
                    url = null,
                    urlToImage = null
                )
            ),
            status = null,
            totalResults = null
        )

        val result = dto.toDomain()

        assertThat(result.status).isEmpty()
        assertThat(result.totalResults).isEqualTo(0)
        assertThat(result.articles).hasSize(1)
        val article = result.articles.first()
        assertThat(article.author).isEmpty()
        assertThat(article.content).isEmpty()
        assertThat(article.description).isEmpty()
        assertThat(article.publishedAt).isEmpty()
        assertThat(article.title).isEmpty()
        assertThat(article.url).isEmpty()
        assertThat(article.urlToImage).isEmpty()
        assertThat(article.source.id).isEmpty()
        assertThat(article.source.name).isEmpty()
    }
}
