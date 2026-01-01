package com.mehdi.bbcnews.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.mehdi.bbcnews.presentation.NewsListViewModel
import com.mehdi.bbcnews.presentation.navigation.destinations.detailNewsComposable
import com.mehdi.bbcnews.presentation.navigation.destinations.mainNewsComposable
import com.mehdi.bbcnews.util.Constants.MAIN_NEWS_SCREEN

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    newsListViewModel: NewsListViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    NavHost(navController = navController, startDestination = MAIN_NEWS_SCREEN) {
        mainNewsComposable(
            navigateToDetailNewsList = screen.main,
            newsListViewModel = newsListViewModel
        )
        detailNewsComposable()
    }
}
