package com.mehdi.bbcnews.component.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.mehdi.bbcnews.domain.model.NewsArticle

class AssetParamType : NavType<NewsArticle>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NewsArticle? {
        return getArticle(bundle, key)
    }

    override fun parseValue(value: String): NewsArticle {
        return Gson().fromJson(value, NewsArticle::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: NewsArticle) {
        bundle.putSerializable(key, value)
    }

    @Suppress("DEPRECATION")
    private fun getArticle(bundle: Bundle, key: String): NewsArticle? {
        return bundle.getSerializable(key) as? NewsArticle
    }
}
