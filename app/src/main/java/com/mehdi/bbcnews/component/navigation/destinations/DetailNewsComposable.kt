package com.mehdi.bbcnews.component.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import com.mehdi.bbcnews.component.ui.screens.detail.DetailNewsList
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.util.AssetParamType
import com.mehdi.bbcnews.util.Constants
import com.mehdi.bbcnews.util.Constants.DETAIL_NEWS_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.detailNewsComposable() {
    composable(
        route = DETAIL_NEWS_SCREEN,
        arguments = listOf(navArgument(Constants.DETAIL_NEWS_ARGUMENT_KEY) {
            type = AssetParamType()
        }),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(
                    durationMillis = 700
                )
            )
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { +it },
                animationSpec = tween(
                    durationMillis = 700
                )
            )
        }
    )
    {
        val article =
            it.arguments?.getParcelable(Constants.DETAIL_NEWS_ARGUMENT_KEY, Article::class.java)

        article?.let { it1 -> DetailNewsList(article = it1) }

    }
}