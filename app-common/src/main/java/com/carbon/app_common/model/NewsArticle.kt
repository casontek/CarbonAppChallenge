package com.carbon.app_common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticle(
    val author: String?,
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
) : Parcelable
