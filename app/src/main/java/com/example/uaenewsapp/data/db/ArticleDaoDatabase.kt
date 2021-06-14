package com.example.uaenewsapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.uaenewsapp.data.beans.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDaoDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}