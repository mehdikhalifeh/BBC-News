package com.mehdi.bbcnews.data.mapper

import com.mehdi.bbcnews.data.dto.ArticleDto
import com.mehdi.bbcnews.domain.model.Article

class ArticleMapper {
    fun toDomain(dto: ArticleDto): Article {
        return Article(
            id = dto.id.orEmpty(),
            title = dto.title.orEmpty(),
            description = dto.description,
            imageUrl = dto.imageUrl
        )
    }
}
