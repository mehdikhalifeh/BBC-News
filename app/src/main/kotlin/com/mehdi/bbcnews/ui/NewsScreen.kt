package com.mehdi.bbcnews.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.mehdi.bbcnews.presentation.news.NewsUiModel
import com.mehdi.bbcnews.presentation.news.NewsUiState
import com.mehdi.bbcnews.presentation.news.NewsViewModel
import coil.compose.AsyncImage

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    onArticleClick: (NewsUiModel) -> Unit
) {
    val state by viewModel.state.collectAsState()

    when (state) {
        NewsUiState.Loading -> LoadingState()
        is NewsUiState.Content -> NewsList(
            articles = (state as NewsUiState.Content).articles,
            onArticleClick = onArticleClick
        )
        is NewsUiState.Error -> ErrorState((state as NewsUiState.Error).message)
    }
}

@Composable
private fun LoadingState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState(message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun NewsList(
    articles: List<NewsUiModel>,
    onArticleClick: (NewsUiModel) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(articles) { article ->
            NewsCard(article, onArticleClick)
        }
    }
}

@Composable
private fun NewsCard(
    article: NewsUiModel,
    onArticleClick: (NewsUiModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onArticleClick(article) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (!article.imageUrl.isNullOrBlank()) {
                AsyncImage(
                    model = article.imageUrl,
                    contentDescription = article.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(text = article.title, style = MaterialTheme.typography.titleMedium)
            if (article.description.isNotBlank()) {
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            if (!article.source.isNullOrBlank()) {
                Text(
                    text = article.source,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
