package com.mehdi.bbcnews.presentation.ui.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mehdi.bbcnews.presentation.model.NewsArticleUi
import com.mehdi.bbcnews.presentation.ui.screens.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNewsList(
    article: NewsArticleUi,
) {
    Scaffold(
        topBar = {
            AppBar(
                source = article.source.name
            )
        },
        content = {
            DetailContent(
                modifier = Modifier.padding(it),
                article = article
            )
        }
    )
}
