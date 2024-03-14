package com.example.apitoroomappdemo.di

import android.content.Context
import androidx.room.Room
import com.example.apitoroomappdemo.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Tirth Patel.
 */

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, NewsDatabase::class.java, "news_db_from_module"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    //Here We are providing database instance from this class there is no need for getDatabase() from NewsDatabase class.
    //Check by run and see database name

}