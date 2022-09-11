package com.carbon.app.util

import com.carbon.app_common.model.NewsArticle
import com.carbon.offlinecache.entity.ArticleEntity

object NewsArticleMapper {

    fun getNewsArticleEntity(article: NewsArticle) = ArticleEntity(
        id = 0,
        title = article.title,
        description = article.description,
        urlToImage = article.urlToImage,
        publishedAt = article.publishedAt,
        content = article.content
    )

    fun getNewsArticle(entity: ArticleEntity) = NewsArticle(
        author = null,
        title = entity.title,
        description = entity.description,
        urlToImage = entity.urlToImage,
        publishedAt = entity.publishedAt,
        content = entity.content
    )

}