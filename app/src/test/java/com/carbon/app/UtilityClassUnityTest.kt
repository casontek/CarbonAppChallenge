package com.carbon.app

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.carbon.app.util.NewsArticleMapper
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.DateConverter
import com.carbon.offlinecache.entity.ArticleEntity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilityClassUnityTest {

    @Test
    fun dateConverterTest() {
        val formattedDate = DateConverter.getConvertedDate("")
        Assert.assertEquals(formattedDate, "Sep 10, 2022 3:20 PM")
    }

    @Test
    fun articleToEntityMapperTest() {
        val article = NewsArticle(
            author = null,
            title = "Test Article",
            description = "Testing data class mapping",
            urlToImage = "https://www.",
            publishedAt = "2022-10-09",
            content = "testing data class"
        )

        val expectedResult = ArticleEntity(
            id = 0,
            title = "Test Article",
            description = "Testing data class mapping",
            urlToImage = "https://www.",
            publishedAt = "2022-10-09",
            content = "testing data class"
        )

        val result  = NewsArticleMapper.getNewsArticleEntity(article)
        Assert.assertEquals(result, expectedResult)

    }

}