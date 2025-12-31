package com.mehdi.bbcnews.data.repository

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.data.datasource.remote.NewsRemoteDataSource
import com.mehdi.bbcnews.data.dto.ArticleDto
import com.mehdi.bbcnews.data.error.DomainErrorMapper
import com.mehdi.bbcnews.data.mapper.ArticleMapper
import com.mehdi.bbcnews.domain.result.DomainError
import com.mehdi.bbcnews.domain.result.Result
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class NewsRepositoryImplTest {
    private val mapper = ArticleMapper()
    private val errorMapper = DomainErrorMapper()

    @Test
    fun `maps remote articles to domain models`() = runTest {
        val dataSource = object : NewsRemoteDataSource {
            override suspend fun getTopHeadlines(): List<ArticleDto> {
                return listOf(
                    ArticleDto(
                        id = "id-1",
                        title = "Title",
                        description = "Desc",
                        imageUrl = "https://image.example/1.jpg"
                    )
                )
            }
        }
        val repository = NewsRepositoryImpl(dataSource, mapper, errorMapper)

        val result = repository.fetchTopHeadlines()

        val article = (result as Result.Success).data.first()
        assertThat(article.id).isEqualTo("id-1")
        assertThat(article.title).isEqualTo("Title")
    }

    @Test
    fun `maps exceptions to domain error`() = runTest {
        val dataSource = object : NewsRemoteDataSource {
            override suspend fun getTopHeadlines(): List<ArticleDto> {
                throw IOException("Network down")
            }
        }
        val repository = NewsRepositoryImpl(dataSource, mapper, errorMapper)

        val result = repository.fetchTopHeadlines()

        assertThat(result).isEqualTo(Result.Failure(DomainError.Network))
    }
}
