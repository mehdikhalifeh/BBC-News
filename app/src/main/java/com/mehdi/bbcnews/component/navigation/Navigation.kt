package com.mehdi.bbcnews.component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.AnimatedNavHost
import com.mehdi.bbcnews.component.NewsListViewModel
import com.mehdi.bbcnews.component.navigation.destinations.detailNewsComposable
import com.mehdi.bbcnews.component.navigation.destinations.mainNewsComposable
import com.mehdi.bbcnews.util.Constants.MAIN_NEWS_SCREEN

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetUpNavigation(
    navController: NavHostController,
    newsListViewModel: NewsListViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }
    AnimatedNavHost(navController = navController, startDestination = MAIN_NEWS_SCREEN) {
        mainNewsComposable(
            navigateToDetailNewsList = screen.main,
            newsListViewModel = newsListViewModel
        )
        detailNewsComposable()
    }
}
import androidx.compose.animation.ExperimentalAnimationApi
