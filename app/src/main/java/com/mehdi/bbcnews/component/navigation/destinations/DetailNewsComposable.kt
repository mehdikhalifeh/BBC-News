package com.mehdi.bbcnews.component.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mehdi.bbcnews.component.model.NewsArticleUi
import com.mehdi.bbcnews.component.navigation.param.NewsArticleParamType
import com.mehdi.bbcnews.component.ui.screens.detail.DetailNewsList
import com.mehdi.bbcnews.util.Constants
import com.mehdi.bbcnews.util.Constants.DETAIL_NEWS_SCREEN

fun NavGraphBuilder.detailNewsComposable() {
    composable(
        route = DETAIL_NEWS_SCREEN,
        arguments = listOf(navArgument(Constants.DETAIL_NEWS_ARGUMENT_KEY) {
            type = NewsArticleParamType()
        })
    ) {
        val article =
            it.arguments?.getParcelable(Constants.DETAIL_NEWS_ARGUMENT_KEY, NewsArticleUi::class.java)

        article?.let { it1 -> DetailNewsList(article = it1) }
    }
}
