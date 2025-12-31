package com.mehdi.bbcnews.domain.repository

import com.mehdi.bbcnews.domain.model.Article
import com.mehdi.bbcnews.domain.result.Result

interface NewsRepository {
    suspend fun fetchTopHeadlines(): Result<List<Article>>
}
