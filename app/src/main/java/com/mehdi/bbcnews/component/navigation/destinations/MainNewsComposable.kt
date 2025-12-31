package com.mehdi.bbcnews.component.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.component.ui.screens.main.MainNewsList
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.util.Constants.MAIN_NEWS_SCREEN

fun NavGraphBuilder.mainNewsComposable(
    navigateToDetailNewsList: (Article) -> Unit,
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
