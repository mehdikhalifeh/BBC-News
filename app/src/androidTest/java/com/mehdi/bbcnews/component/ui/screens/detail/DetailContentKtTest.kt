package com.mehdi.bbcnews.component.ui.screens.detail

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.model.responses.Source
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailContentTest() {
        // Arrange
        val article = Article(
            author = "BBC News",
            content = "A look back at some of the funniest moments from Dame Edna Everage.\r\nShe was one of comedian Barry Humphries' most known characters. Humphries has died at the age of 89.\r\nRead more about the star's l… [+8 chars]",
            description = "A look back at some laughs from the comedian, Barry Humphries, best known for character Dame Edna Everage.",
            publishedAt = "2023-04-22T15:37:19.3827616Z",
            source = Source(id = "bbc-news", name = "BBC News"),
            title = "Dame Edna's memorable moments in 60 seconds",
            url = "http://www.bbc.co.uk/news/entertainment-arts-65358301",
            urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/4EE2/production/_129449102_b5f6c0752bee38fc657f098fb3387e303ad56ccb0_290_2364_13291000x563.jpg"
        )

        // Act
        composeTestRule.setContent {
            DetailContent(
                modifier = Modifier,
                article = article
            )
        }

        // Assert
        composeTestRule.onNodeWithText(article.title.orEmpty()).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.description.orEmpty()).assertIsDisplayed()
        composeTestRule.onNodeWithText(article.content.orEmpty()).assertIsDisplayed()
    }
}
