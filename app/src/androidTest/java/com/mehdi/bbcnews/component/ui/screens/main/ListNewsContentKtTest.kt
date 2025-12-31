package com.mehdi.bbcnews.component.ui.screens.main

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.R
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.component.model.NewsArticleUi
import com.mehdi.bbcnews.component.model.NewsResponseUi
import com.mehdi.bbcnews.component.model.NewsSourceUi
import com.mehdi.bbcnews.component.state.NewsListUiState
import com.mehdi.bbcnews.domain.result.DomainError
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class ListContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val newsListViewModel:NewsListViewModel = mockk(relaxed = true)
    @Test
    fun givenSuccessState_thenHeadlinesListDisplayed() {
        // Given
        val topHeadlines = NewsListUiState.Content(
            NewsResponseUi(
                listOf(
                    NewsArticleUi(
                        author = "BBC News",
                        content = "More than 3,000 people have been evacuated from their homes in the Russian city of Belgorod after an undetonated explosive was found.\r\nIt comes two days after Russia accidentally dropped a bomb on th… [+1573 chars]",
                        description = "An undetonated bomb was found in Belgorod, where a jet accidentally dropped another bomb days earlier.",
                        publishedAt = "2023-04-22T14:37:14.7569359Z",
                        source = NewsSourceUi(id = "bbc-news", name = "BBC News"),
                        title = "Russia's Belgorod sees mass evacuations over undetonated bomb",
                        url = "http://www.bbc.co.uk/news/world-europe-65358070",
                        urlToImage = "https://ichef.bbci.co.uk/news/1024/branded_news/437A/production/_129447271_gettyimages-1252028554.jpg"
                    )
                ),
                "ok",
                1,
            )
        )
        var clickedArticle: NewsArticleUi? = null
        val navigateToDetailNewsList: (NewsArticleUi) -> Unit = { article ->
            clickedArticle = article
        }

        // When
        composeTestRule.setContent {
            ListContent(
                modifier = Modifier,
                topHeadlines = topHeadlines,
                navigateToDetailNewsList = navigateToDetailNewsList,
                newsListViewModel = newsListViewModel
            )
        }

        // Then
        composeTestRule.onNodeWithText(topHeadlines.response.articles[0].title).assertIsDisplayed()
        composeTestRule.onNodeWithText(topHeadlines.response.articles[0].description)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(topHeadlines.response.articles[0].content).assertIsDisplayed()


        val articleToClick = topHeadlines.response.articles[0]
        composeTestRule.onNodeWithText(topHeadlines.response.articles[0].title).performClick()
        assertThat(clickedArticle).isEqualTo(articleToClick)
    }

    @Test
    fun givenLoadingState_thenShimmerListDisplayed() {
        // Given
        val topHeadlines = NewsListUiState.Loading
        val navigateToDetailNewsList: (NewsArticleUi) -> Unit = {}

        // When
        composeTestRule.setContent {
            ListContent(
                modifier = Modifier,
                topHeadlines = topHeadlines,
                navigateToDetailNewsList = navigateToDetailNewsList,
                newsListViewModel = newsListViewModel
            )
        }

        // Then
        composeTestRule.onNodeWithTag("shimmerListItem0")
            .assertExists()
    }


    @Test
    fun givenErrorState_thenEmptyContentDisplayed() {
        // Given
        val topHeadlines = NewsListUiState.Error(DomainError.Unknown())
        val navigateToDetailNewsList: (NewsArticleUi) -> Unit = {}

        // When
        composeTestRule.setContent {
            ListContent(
                modifier = Modifier,
                topHeadlines = topHeadlines,
                navigateToDetailNewsList = navigateToDetailNewsList,
                newsListViewModel = newsListViewModel
            )
        }
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val sadFaceContentDescription = context.resources.getString(R.string.sad_face_icon)
        val emptyContent = context.resources.getString(R.string.empty_content)

        // Then
        composeTestRule.onNodeWithContentDescription(sadFaceContentDescription)
            .assertIsDisplayed()
        composeTestRule.onNodeWithText(emptyContent)
            .assertIsDisplayed()

    }

}
