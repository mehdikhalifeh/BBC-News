package com.mehdi.bbcnews.component.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mehdi.bbcnews.component.ui.screens.detail.DetailNewsList
import com.mehdi.bbcnews.data.model.responses.Article
import com.mehdi.bbcnews.data.util.AssetParamType
import com.mehdi.bbcnews.util.Constants
import com.mehdi.bbcnews.util.Constants.DETAIL_NEWS_SCREEN

fun NavGraphBuilder.detailNewsComposable() {
    composable(
        route = DETAIL_NEWS_SCREEN,
        arguments = listOf(navArgument(Constants.DETAIL_NEWS_ARGUMENT_KEY) {
            type = AssetParamType()
        })
    ) {
        val article =
            it.arguments?.getParcelable(Constants.DETAIL_NEWS_ARGUMENT_KEY, Article::class.java)

        article?.let { it1 -> DetailNewsList(article = it1) }
    }
}
