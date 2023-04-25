package com.mehdi.bbcnews.data.remote.connection

import com.mehdi.bbcnews.BuildConfig
import com.mehdi.bbcnews.data.model.responses.BbcNewsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsListApi {
    @Headers("x-api-key: ${BuildConfig.API_KEY}")
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") source: String,
    ): BbcNewsResponse
}

