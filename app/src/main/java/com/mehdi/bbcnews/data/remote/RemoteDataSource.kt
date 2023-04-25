package com.mehdi.bbcnews.data.remote

import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse


interface RemoteDataSource {
    suspend fun getTopHeadlines(source: String): BbcNewsResponse
}