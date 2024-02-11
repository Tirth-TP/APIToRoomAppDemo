package com.example.apitoroomappdemo.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.apitoroomappdemo.data.model.local.NewsArticle

/**
 * Created by Tirth Patel.
 */
@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(news: List<NewsArticle>)

    @Query("SELECT * FROM article")
    suspend fun getNews(): List<NewsArticle>

    @Delete
    suspend fun deleteAllNews(news: NewsArticle)

    @Query("DELETE FROM article WHERE id = :id")
    suspend fun deleteNews(id: Long)


    @Update
    suspend fun updateNews(news: NewsArticle)
}