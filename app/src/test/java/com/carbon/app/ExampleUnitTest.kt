package com.carbon.app

import com.carbon.app.util.NewsArticleMapper
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.DateConverter
import com.carbon.offlinecache.entity.ArticleEntity
import org.junit.Test

import org.junit.Assert.*


class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun dateConverterTest() {
        val formattedDate = DateConverter.getConvertedDate("")
        assertEquals(formattedDate, "Sep 10, 2022 3:20 PM")
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
        assertEquals(result, expectedResult)
    }

}