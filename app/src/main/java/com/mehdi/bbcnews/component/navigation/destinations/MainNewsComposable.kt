package com.mehdi.bbcnews.component.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.component.ui.screens.main.MainNewsList
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.util.Constants.MAIN_NEWS_SCREEN

@OptIn(ExperimentalAnimationApi::class)
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