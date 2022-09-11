package com.carbon.offlinecache

import android.content.Context
import androidx.room.Room
import org.junit.Assert.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carbon.offlinecache.dao.ArticleDao
import com.carbon.offlinecache.entity.ArticleEntity
import kotlinx.coroutines.runBlocking


import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CacheDatabaseTest {
    private lateinit var database: CacheDatabase
    private lateinit var cacheDao: ArticleDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CacheDatabase::class.java).build()
        cacheDao = database.articleDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testNewsArticleCache() = runBlocking {
        var article = ArticleEntity(
            id = 0,
            title = "New York Time News",
            description = "Latest news from News API",
            urlToImage = "https://www.",
            publishedAt = "2022-10-09 10:30 PM",
            content = "News flash from News API"
        )
        cacheDao.addArticle(article)
        val result = cacheDao.getNewsArticles()
        assertTrue(result.isNotEmpty())
    }

}