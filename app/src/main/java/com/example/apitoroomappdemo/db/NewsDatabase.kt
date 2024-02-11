package com.example.apitoroomappdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apitoroomappdemo.data.model.local.NewsArticle

/**
 * Created by Tirth Patel.
 */

@Database(entities = [NewsArticle::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        @Volatile
        private var INSTANCE: NewsDatabase? = null

        fun getDatabase(context: Context): NewsDatabase {
            if (INSTANCE != null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NewsDatabase::class.java,
                        "news_db"
                    )
                        .build()
                }
            }
            return INSTANCE!!
        }
    }


}