package com.mehdi.bbcnews.presentation.model

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.domain.model.NewsArticle
import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.model.NewsSource
import org.junit.Test

class NewsUiMapperTest {

    @Test
    fun `toUi maps domain response to ui response`() {
        val response = NewsResponse(
            articles =
                listOf(
                    NewsArticle(
                        author = "BBC News",
                        content = "Some content",
                        description = "Some description",
                        publishedAt = "2023-05-01T10:00:00Z",
                        source = NewsSource(id = "bbc-news", name = "BBC News"),
                        title = "Headline",
                        url = "http://www.bbc.co.uk/news/example",
                        urlToImage = "http://image",
                    )
                ),
            status = "ok",
            totalResults = 1,
        )

        val uiResponse = response.toUi()

        assertThat(uiResponse).isEqualTo(
            NewsResponseUi(
                articles =
                    listOf(
                        NewsArticleUi(
                            author = "BBC News",
                            content = "Some content",
                            description = "Some description",
                            publishedAt = "2023-05-01T10:00:00Z",
                            source = NewsSourceUi(id = "bbc-news", name = "BBC News"),
                            title = "Headline",
                            url = "http://www.bbc.co.uk/news/example",
                            urlToImage = "http://image",
                        )
                    ),
                status = "ok",
                totalResults = 1,
            )
        )
    }
}
