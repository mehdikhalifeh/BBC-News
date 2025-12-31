package com.mehdi.bbcnews.data.datasource.remote

import com.mehdi.bbcnews.data.dto.ArticleDto

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(): List<ArticleDto>
}
