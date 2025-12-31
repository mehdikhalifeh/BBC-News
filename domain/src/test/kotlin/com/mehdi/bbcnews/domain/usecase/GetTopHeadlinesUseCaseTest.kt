package com.mehdi.bbcnews.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.mehdi.bbcnews.domain.model.Article
import com.mehdi.bbcnews.domain.repository.NewsRepository
import com.mehdi.bbcnews.domain.result.Result
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTopHeadlinesUseCaseTest {

    @Test
    fun `returns repository data`() = runTest {
        val expected = listOf(
            Article(
                id = "1",
                title = "Headline",
                description = "Description",
                imageUrl = "https://image.example/1.jpg"
            )
        )
        val repository = object : NewsRepository {
            override suspend fun fetchTopHeadlines(): Result<List<Article>> {
                return Result.Success(expected)
            }
        }

        val useCase = GetTopHeadlinesUseCase(repository)

        val result = useCase()

        assertThat(result).isEqualTo(Result.Success(expected))
    }
}
