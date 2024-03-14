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

        //There is no user of this code this is not reachable.
        //We are providing database instance from DbModule object.
        fun getDatabase(context: Context): NewsDatabase {
            //If instance is null then create
            if (INSTANCE == null) {
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