package com.example.uaenewsapp.data.api

import androidx.room.withTransaction
import com.example.uaenewsapp.data.db.ArticleDaoDatabase
import com.example.uaenewsapp.data.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewApiRepo @Inject constructor(
    private val api: NewsApi,
    private val db: ArticleDaoDatabase
) {


    suspend fun onlineData()=api.getNews()


    private val articleDao = db.articleDao()

    fun getNews() = networkBoundResource(
        query = {
            articleDao.getAllNews()
        },
        fetch = {
            delay(2000)
            api.getNews().articles
        },
        saveFetchResult = { arts ->
            db.withTransaction {
                articleDao.deleteAllNews()
                articleDao.insertNewa(arts)
            }
        }
    )
}