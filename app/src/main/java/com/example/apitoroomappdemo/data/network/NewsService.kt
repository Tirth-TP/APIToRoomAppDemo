package com.example.apitoroomappdemo.data.network

import com.example.apitoroomappdemo.data.model.remote.NewsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.apitoroomappdemo.utils.Constant as Const

/**
 * Created by Tirth Patel.
 */
interface NewsService {

    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String = Const.Q,
        @Query("from") from: String = Const.FROM_DATE,
        @Query("to") to: String = Const.TO_DATE,
        @Query("sortBy") sortBy: String = Const.SORT_BY,
        @Query("apiKey") apiKey: String = Const.API_KEY,
    ): Response<NewsList>
}
