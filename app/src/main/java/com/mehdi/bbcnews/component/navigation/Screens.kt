package com.mehdi.bbcnews.component.navigation

import android.net.Uri
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.mehdi.bbcnews.data.model.responses.Article

class Screens(navController: NavHostController) {

    val main: (Article) -> Unit = { article ->
        val json = Uri.encode(Gson().toJson(article))
        navController.navigate(route = "detailNews/${json}")
    }
    val detail: () -> Unit = {
        navController.navigate(route = "mainNews")
    }
}