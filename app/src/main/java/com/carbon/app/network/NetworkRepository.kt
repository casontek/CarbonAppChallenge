package com.carbon.app.network

import com.carbon.app_common.utils.Constants
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val service: RestService) {

    suspend fun fetchNewsArticles(from: String) =
        service.getNewtsArticles(from, "popularity", Constants.apiKey)
}