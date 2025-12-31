package com.mehdi.bbcnews.component.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mehdi.bbcnews.R
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.component.model.NewsArticleUi
import com.mehdi.bbcnews.component.model.NewsResponseUi
import com.mehdi.bbcnews.component.model.NewsSourceUi
import com.mehdi.bbcnews.component.state.NewsListUiState
import com.mehdi.bbcnews.component.ui.screens.EmptyContent
import com.mehdi.bbcnews.component.ui.screens.loading.ShimmerListItem
import com.mehdi.bbcnews.component.ui.theme.CARD_ELEVATION
import com.mehdi.bbcnews.component.ui.theme.HEIGHT_LINE
import com.mehdi.bbcnews.component.ui.theme.LARGE_PADDING
import com.mehdi.bbcnews.component.ui.theme.MEDIUM_PADDING
import com.mehdi.bbcnews.component.ui.theme.NEWS_CORNER_SIZE
import com.mehdi.bbcnews.component.ui.theme.NEWS_IMAGE_HEIGHT
import com.mehdi.bbcnews.component.ui.theme.SPACER_TEXT_SIZE
import com.mehdi.bbcnews.util.Constants.SHIMMER_ITEM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    modifier: Modifier,
    newsListViewModel: NewsListViewModel,
    navigateToDetailNewsList: (NewsArticleUi) -> Unit,
    topHeadlines: NewsListUiState,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        state = pullToRefreshState,
        isRefreshing = topHeadlines is NewsListUiState.Loading,
        onRefresh = { newsListViewModel.onRefresh() },
        modifier = modifier
    ) {
        when (topHeadlines) {
            is NewsListUiState.Content -> {
                HandleListContent(
                    modifier = Modifier,
                    topHeadlines = topHeadlines.response,
                    navigateToDetailNewsList = navigateToDetailNewsList
                )
            }

            is NewsListUiState.Error -> EmptyContent()

            is NewsListUiState.Loading -> ShowShimmer(
                modifier = Modifier, 10
            )
        }
    }
}


@Composable
fun ShowShimmer(
    modifier: Modifier, articleSize: Int
) {
    LazyColumn(modifier = modifier.padding(all = MEDIUM_PADDING)) {
        items(count = articleSize) {
            ShimmerListItem(
                modifier = Modifier.testTag(SHIMMER_ITEM.plus(it))
            )
        }
    }
}

@Composable
fun HandleListContent(
    modifier: Modifier,
    topHeadlines: NewsResponseUi,
    navigateToDetailNewsList: (NewsArticleUi) -> Unit,
) {
    if (topHeadlines.articles.isEmpty()) {
        EmptyContent()
    } else {
        HeadlinesList(
            modifier = modifier,
            topHeadlines = topHeadlines,
            navigateToDetailNewsList = navigateToDetailNewsList
        )
    }
}

@Composable
fun HeadlinesList(
    modifier: Modifier,
    topHeadlines: NewsResponseUi,
    navigateToDetailNewsList: (NewsArticleUi) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(items = topHeadlines.articles) { article ->
            HeadlinesRow(
                modifier = Modifier,
                article = article,
                navigateToDetailNewsList = navigateToDetailNewsList
            )
        }
    }
}


@Composable
private fun NewsImage(article: NewsArticleUi) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(article.urlToImage.orEmpty())
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(MEDIUM_PADDING)
            .size(NEWS_IMAGE_HEIGHT)
            .clip(RoundedCornerShape(corner = CornerSize(NEWS_CORNER_SIZE)))
    )
}

@Composable
fun HeadlinesRow(
    modifier: Modifier,
    article: NewsArticleUi,
    navigateToDetailNewsList: (NewsArticleUi) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = MEDIUM_PADDING, vertical = MEDIUM_PADDING)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                navigateToDetailNewsList(article)
            }, elevation = CardDefaults.cardElevation(
            defaultElevation = CARD_ELEVATION
        ), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ), shape = RoundedCornerShape(corner = CornerSize(NEWS_CORNER_SIZE))

    ) {
        Row {
            NewsImage(article = article)
            Column(
                modifier = Modifier
                    .padding(all = LARGE_PADDING)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = article.title.orEmpty(),
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily.SansSerif,
                    lineHeight = HEIGHT_LINE,
                    fontWeight = FontWeight.Bold,

                    )
                Spacer(modifier = Modifier.height(SPACER_TEXT_SIZE))
                Text(
                    text = article.description.orEmpty(),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.SansSerif,
                    lineHeight = HEIGHT_LINE,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(SPACER_TEXT_SIZE))
                Text(
                    text = article.content.orEmpty(),
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.SansSerif,
                    lineHeight = HEIGHT_LINE,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
    }
}

@Preview
@Composable
fun HeadlinesRowPreview() {
    HeadlinesRow(
        modifier = Modifier, article = NewsArticleUi(
            title = "Fire breaks out on US bridge after fuel tanker explosion",
            description = "One person was killed in the crash which shut down a major motorway.",
            author = "",
            content = "British diplomats and their families have been evacuated from Sudan in a \"complex and rapid\" operation, Prime Minister Rishi Sunak has confirmed. \r\nMr Sunak said work was continuing to ensure the saf… [+870 chars]",
            source = NewsSourceUi(id = "", name = ""),
            publishedAt = "",
            url = "",
            urlToImage = ""
        )
    ) {}
}
