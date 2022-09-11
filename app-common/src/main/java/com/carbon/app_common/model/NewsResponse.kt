package com.carbon.app_common.model

data class NewsResponse(
    val status: String,
    val code: String?,
    val totalResults: Int?,
    val articles: List<NewsArticle>?
)
