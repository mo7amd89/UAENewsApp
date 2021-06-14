package com.example.uaenewsapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.uaenewsapp.data.beans.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllNews(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewa(restaurants: List<Article>)

    @Query("DELETE FROM article")
    suspend fun deleteAllNews()
}
