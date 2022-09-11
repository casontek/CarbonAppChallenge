package com.carbon.offlinecache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)