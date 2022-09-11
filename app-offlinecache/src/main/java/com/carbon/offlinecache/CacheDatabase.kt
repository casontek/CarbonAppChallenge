package com.carbon.offlinecache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carbon.offlinecache.dao.ArticleDao
import com.carbon.offlinecache.entity.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1,
    exportSchema = false)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun articleDao() : ArticleDao
}