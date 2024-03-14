package com.example.apitoroomappdemo.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Tirth Patel.
 */

@Entity(tableName = "article")
data class NewsArticle(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,
) /*{
    // Empty constructor
    constructor() : this(0, "", "", "", "", "", "", "")
}  //No need */