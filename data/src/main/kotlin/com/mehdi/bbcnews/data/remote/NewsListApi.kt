package com.mehdi.bbcnews.data.remote

import com.mehdi.bbcnews.data.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsListApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source: String,
    ): NewsResponseDto
}
