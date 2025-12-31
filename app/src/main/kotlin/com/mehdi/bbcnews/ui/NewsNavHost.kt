package com.mehdi.bbcnews.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mehdi.bbcnews.presentation.news.NewsUiModel
import com.mehdi.bbcnews.presentation.news.NewsViewModel

private const val ROUTE_LIST = "list"
private const val ROUTE_DETAIL = "detail"

@Composable
fun NewsNavHost(
    viewModel: NewsViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = ROUTE_LIST) {
        composable(ROUTE_LIST) {
            NewsScreen(
                viewModel = viewModel,
                onArticleClick = { article ->
                    viewModel.selectArticle(article)
                    navController.navigate(ROUTE_DETAIL)
                }
            )
        }
        composable(ROUTE_DETAIL) {
            val article by viewModel.selectedArticle.collectAsState()
            ArticleDetailScreen(
                article = article,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
private fun ArticleDetailScreen(
    article: NewsUiModel?,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    if (article == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = "No article selected", style = MaterialTheme.typography.titleMedium)
            Button(onClick = onBack) {
                Text(text = "Back")
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (!article.imageUrl.isNullOrBlank()) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Text(text = article.title, style = MaterialTheme.typography.headlineSmall)
        if (!article.source.isNullOrBlank()) {
            Text(text = article.source, style = MaterialTheme.typography.labelLarge)
        }
        if (!article.publishedAt.isNullOrBlank()) {
            Text(text = article.publishedAt, style = MaterialTheme.typography.labelMedium)
        }
        if (article.description.isNotBlank()) {
            Text(text = article.description, style = MaterialTheme.typography.bodyLarge)
        }
        if (!article.content.isNullOrBlank()) {
            Text(text = article.content, style = MaterialTheme.typography.bodyMedium)
        }
        if (!article.url.isNullOrBlank()) {
            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                context.startActivity(intent)
            }) {
                Text(text = "Read full article")
            }
        }
        Button(onClick = onBack) {
            Text(text = "Back")
        }
    }
}
