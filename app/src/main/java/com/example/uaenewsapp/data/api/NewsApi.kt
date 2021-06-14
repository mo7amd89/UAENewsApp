package com.example.uaenewsapp.data.api

import com.example.uaenewsapp.data.beans.ApiResponse
import retrofit2.http.GET

interface NewsApi {

    @GET("top-headlines?country=ae&apiKey=71f81d9d7d054d34836780e266e2e2d9")
    suspend fun getNews(): ApiResponse
}