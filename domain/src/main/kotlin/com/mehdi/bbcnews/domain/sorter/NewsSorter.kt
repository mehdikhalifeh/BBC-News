package com.mehdi.bbcnews.domain.sorter

import com.mehdi.bbcnews.domain.model.NewsArticle
import java.time.Instant

class NewsSorter {
    fun sort(articles: List<NewsArticle>): List<NewsArticle> {
        return runCatching {
            articles.sortedByDescending { article ->
                Instant.parse(article.publishedAt)
            }
        }.getOrElse { articles }
    }
}
