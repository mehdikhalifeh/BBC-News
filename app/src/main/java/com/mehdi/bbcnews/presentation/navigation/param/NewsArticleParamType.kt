package com.mehdi.bbcnews.presentation.navigation.param

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.mehdi.bbcnews.presentation.model.NewsArticleUi

class NewsArticleParamType : NavType<NewsArticleUi>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NewsArticleUi? {
        return bundle.getParcelable(key, NewsArticleUi::class.java)
    }

    override fun parseValue(value: String): NewsArticleUi {
        return Gson().fromJson(value, NewsArticleUi::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: NewsArticleUi) {
        bundle.putParcelable(key, value)
    }
}
