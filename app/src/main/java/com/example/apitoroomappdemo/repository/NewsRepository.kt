package com.example.apitoroomappdemo.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apitoroomappdemo.data.model.local.NewsArticle
import com.example.apitoroomappdemo.data.network.NewsService
import com.example.apitoroomappdemo.db.NewsDatabase
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Tirth Patel.
 */

@Singleton
class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val newsDatabase: NewsDatabase,
) {
    /*
    //For Api data pass to view
    private val _newsList = MutableLiveData<NewsList>()

     val newsList: LiveData<NewsList>
         get() = _newsList  */

    private val _newsList = MutableLiveData<List<NewsArticle>>()

    val newsList: LiveData<List<NewsArticle>>
        get() = _newsList

    suspend fun getNewsList() {
        try {
            val result = newsService.getNews()
            val newsArticles = result.body()?.articles?.map { article ->
                // Map Article to NewsArticle entity
                NewsArticle(
                    id = 0, // Auto-generated ID
                    author = article.author,
                    content = article.content,
                    description = article.description,
                    publishedAt = article.publishedAt,
                    title = article.title,
                    url = article.url,
                    urlToImage = article.urlToImage
                )
            }
            val newsDao = newsDatabase.newsDao()
            if (newsDao.getNews().isEmpty()) {
                newsArticles?.let {
                    newsDao.addNews(it)
                }
            }

            val newsArticle = newsDatabase.newsDao().getNews()
            _newsList.postValue(newsArticle)


        } catch (e: Exception) {
            println("Issue : $e ")
        }
    }

    suspend fun deleteAllNews(news: NewsArticle) {
        newsDatabase.newsDao().deleteAllNews(news)
    }

    suspend fun deleteNews(id: Long) {
        newsDatabase.newsDao().deleteNews(id)
        val updatedNewsList = newsDatabase.newsDao().getNews()
        _newsList.postValue(updatedNewsList)
    }

    suspend fun updatedNews(newsList: NewsArticle) {
        newsDatabase.newsDao().updateNews(newsList)
        val updatedNewsList = newsDatabase.newsDao().getNews()
        _newsList.postValue(updatedNewsList)
    }
}