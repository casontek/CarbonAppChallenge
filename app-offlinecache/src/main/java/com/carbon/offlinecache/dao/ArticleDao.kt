package com.carbon.offlinecache.dao

import androidx.room.*
import com.carbon.offlinecache.entity.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticles(articles: List<ArticleEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addArticle(articles: ArticleEntity)

    @Query("delete from ArticleEntity")
    suspend fun clearDB()

    @Query("select * from ArticleEntity")
    suspend fun getNewsArticles() : List<ArticleEntity>

}