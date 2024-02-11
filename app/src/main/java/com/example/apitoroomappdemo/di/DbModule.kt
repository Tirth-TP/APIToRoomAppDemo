package com.example.apitoroomappdemo.di

import android.content.Context
import androidx.room.Room
import com.example.apitoroomappdemo.data.model.local.NewsArticle
import com.example.apitoroomappdemo.db.NewsDao
import com.example.apitoroomappdemo.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by Tirth Patel.
 */

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NewsDatabase::class.java, "news_db"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

}