package com.mehdi.bbcnews.presentation.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mehdi.bbcnews.R
import com.mehdi.bbcnews.presentation.NewsListViewModel
import com.mehdi.bbcnews.presentation.model.NewsArticleUi
import com.mehdi.bbcnews.presentation.ui.screens.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNewsList(
    navigateToDetailNewsList: (NewsArticleUi) -> Unit,
    newsListViewModel: NewsListViewModel
) {
    val topHeadlines by newsListViewModel.topHeadlines.collectAsState()

    Scaffold(
        topBar = {
            AppBar(
                source = stringResource(id = R.string.app_name)
            )
        },
        content = {
            ListContent(
                modifier = Modifier.padding(it),
                topHeadlines = topHeadlines,
                newsListViewModel = newsListViewModel,
                navigateToDetailNewsList = navigateToDetailNewsList
            )
        }
    )
}
