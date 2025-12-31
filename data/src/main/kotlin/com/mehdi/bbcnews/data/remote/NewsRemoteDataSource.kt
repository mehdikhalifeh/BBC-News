package com.mehdi.bbcnews.data.remote

import com.mehdi.bbcnews.data.dto.NewsResponseDto

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(source: String): NewsResponseDto
}
