package com.mehdi.bbcnews.domain

import com.mehdi.bbcnews.domain.model.NewsResponse

interface Repository {
    suspend fun getTopHeadlines(source: String): NewsResponse
}
