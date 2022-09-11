package com.carbon.app.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbon.app.network.NetworkRepository
import com.carbon.app.util.NewsArticleMapper
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.Result
import com.carbon.offlinecache.entity.ArticleEntity
import com.carbon.offlinecache.repository.CacheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NetworkRepository,
    private val cacheRepo: CacheRepository
)
    : ViewModel() {
    private var articles: List<NewsArticle>? = null

    fun fetchNewsArticles(date: String) : Flow<Result<List<NewsArticle>>> = flow {
            emit(Result.loading())
            try{
                repository.fetchNewsArticles(date).let {
                    if(it.isSuccessful) {
                        val data = it.body()!!
                        if(data.status == "ok") {
                            val articles = data.articles
                            emit(Result.success("success", articles))

                            val entities = arrayListOf<ArticleEntity>()
                            data.articles?.forEach { it2 -> entities.add(NewsArticleMapper.getNewsArticleEntity(it2)) }
                            cacheOffline(entities)
                        }
                        else emit(Result.error("no response. try again!"))
                    }
                    else {
                        emit(Result.error("api error. Try gain!"))
                        Log.d("_&error", "Error ingo: ${it.raw().networkResponse()}")
                    }
                }
            }
            catch (e: Exception) {
                emit(Result.error("network issue. Try gain!"))
                Log.d("_&error", "Error ingo: $e")
            }
        }

    fun setNewsArticles(newsArticles: List<NewsArticle>){
        this.articles = newsArticles
    }

    fun getNewsArticles() : List<NewsArticle>? = articles

    private suspend fun cacheOffline(articles: List<ArticleEntity>) {
        cacheRepo.clearPreviousArticles()
        cacheRepo.addArticles(articles)
    }

    suspend fun offlineNewsArticles() : List<ArticleEntity> = cacheRepo.getNewsArticles()


}