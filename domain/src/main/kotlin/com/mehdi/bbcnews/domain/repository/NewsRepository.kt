package com.mehdi.bbcnews.domain.repository

import com.mehdi.bbcnews.domain.model.NewsResponse
import com.mehdi.bbcnews.domain.result.Result

interface NewsRepository {
    suspend fun getTopHeadlines(source: String): Result<NewsResponse>
}
