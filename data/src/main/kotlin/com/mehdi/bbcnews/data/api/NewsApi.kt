package com.mehdi.bbcnews.data.api

import com.mehdi.bbcnews.data.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String = "us"
    ): NewsResponseDto
}
