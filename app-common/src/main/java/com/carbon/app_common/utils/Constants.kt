package com.carbon.app_common.utils

import android.content.Context
import android.net.ConnectivityManager

object Constants {
    val baseUrl = "https://newsapi.org"
    val apiKey = "fa079b0757154dbf9186d96ae5216e53"

    fun isConnected(c: Context): Boolean {
        val manager = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var connected = false
        if (manager != null) {
            val info = manager.activeNetworkInfo
            connected = info != null && info.isConnected
        }
        return connected
    }

}