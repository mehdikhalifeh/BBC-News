package com.mehdi.bbcnews.presentation.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mehdi.bbcnews.presentation.NewsListViewModel
import com.mehdi.bbcnews.presentation.model.NewsArticleUi
import com.mehdi.bbcnews.presentation.ui.screens.main.MainNewsList
import com.mehdi.bbcnews.util.Constants.MAIN_NEWS_SCREEN

fun NavGraphBuilder.mainNewsComposable(
    navigateToDetailNewsList: (NewsArticleUi) -> Unit,
    newsListViewModel: NewsListViewModel
) {
    composable(
        route = MAIN_NEWS_SCREEN
    )
    {
        MainNewsList(
            navigateToDetailNewsList = navigateToDetailNewsList,
            newsListViewModel = newsListViewModel,
        )
    }
}
