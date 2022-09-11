package com.carbon.app.network

import com.carbon.app_common.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestService {
    @GET("/v2/everything")
    suspend fun getNewtsArticles(
        @Query("from") from: String,
        @Query("sortBy") sortKey: String,
        @Query("apiKey") key: String,
        @Query("sources") source: String = "bbc-news"
    ) : Response<NewsResponse>

}