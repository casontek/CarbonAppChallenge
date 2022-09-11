package com.carbon.app

import com.carbon.app.util.NewsArticleMapper
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.DateConverter
import com.carbon.offlinecache.entity.ArticleEntity
import org.junit.Assert
import org.junit.Test


class UtilityClassUnityTest {

    @Test
    fun dateConverterTest() {
        val formattedDate = DateConverter.getConvertedDate("2022-09-11T07:20:37Z")
        Assert.assertEquals(formattedDate, "Sep 11, 2022 8:20 AM")
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