package com.carbon.offlinecache

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideOfflineCache(@ApplicationContext c : Context) : CacheDatabase {
        return Room.databaseBuilder(
            c,
            CacheDatabase::class.java,
            "OfflineCache"
        ).build()
    }

    @Provides
    @Singleton
    fun providesArticleDao(cacheDatabase: CacheDatabase) = cacheDatabase.articleDao()

}