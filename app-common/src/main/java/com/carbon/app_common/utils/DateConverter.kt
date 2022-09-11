package com.carbon.app_common.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateConverter {

    @SuppressLint("SimpleDateFormat")
    fun getConvertedDate(dateTime : String) : String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(dateTime)
        return SimpleDateFormat("MMM d, yyyy h:mm a").format(format)
    }

}