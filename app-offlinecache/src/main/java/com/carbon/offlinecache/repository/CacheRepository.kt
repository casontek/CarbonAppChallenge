package com.carbon.offlinecache.repository

import com.carbon.offlinecache.dao.ArticleDao
import com.carbon.offlinecache.entity.ArticleEntity
import javax.inject.Inject

class CacheRepository @Inject constructor(private val articleDao: ArticleDao) {

    suspend fun getNewsArticles() = articleDao.getNewsArticles()

    suspend fun clearPreviousArticles() = articleDao.clearDB()

    suspend fun addArticles(articles: List<ArticleEntity>) =
        articleDao.addArticles(articles)

}