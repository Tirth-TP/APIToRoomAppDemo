package com.example.apitoroomappdemo.data.model.remote

data class NewsList(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)