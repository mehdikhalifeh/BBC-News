package com.mehdi.bbcnews.component.ui.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mehdi.bbcnews.component.ui.screens.AppBar
import com.mehdi.bbcnews.data.model.responses.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailNewsList(
    article: Article,
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
