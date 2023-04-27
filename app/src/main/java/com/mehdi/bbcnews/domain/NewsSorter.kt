package com.mehdi.bbcnews.domain

import com.mehdi.bbcnews.data.model.responses.Article
import java.time.Instant

class NewsSorter {
    fun sort(articles: List<Article>): List<Article> {
         return try{
            articles.sortedByDescending { article ->
                Instant.parse(article.publishedAt)
            }
        } catch (e: Throwable) {
            println(e.message)
            emptyList()
        }
    }
}
