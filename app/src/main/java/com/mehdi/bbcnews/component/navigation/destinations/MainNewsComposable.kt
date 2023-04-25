package com.mehdi.bbcnews.component.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.util.Constants.MAIN_NEWS_SCREEN
import com.mehdi.bbcnews.component.ui.screens.main.MainNewsList
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.util.Constants

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.mainNewsComposable(
    navigateToDetailNewsList: (Article) -> Unit,
    newsListViewModel: NewsListViewModel
) {
    composable(
        route = MAIN_NEWS_SCREEN
    )
    {
        val source = Constants.NEWS_SOURCE

        LaunchedEffect(key1 = source) {
            newsListViewModel.getTopHeadlines(source = source)
        }

        MainNewsList(
            navigateToDetailNewsList = navigateToDetailNewsList,
            newsListViewModel = newsListViewModel,
        )
    }
}