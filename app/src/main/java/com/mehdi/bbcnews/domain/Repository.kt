package com.mehdi.bbcnews.domain

import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse

interface Repository {
    suspend fun getTopHeadlines(source: String): BbcNewsResponse
}