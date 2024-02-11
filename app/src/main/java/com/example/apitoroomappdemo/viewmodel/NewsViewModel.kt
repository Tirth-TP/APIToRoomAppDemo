package com.example.apitoroomappdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apitoroomappdemo.data.model.local.NewsArticle
import com.example.apitoroomappdemo.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Tirth Patel.
 */
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    /*     val newsList: LiveData<NewsList>
            get() = newsRepository.newsList   */

    val newsList: LiveData<List<NewsArticle>>
        get() = newsRepository.newsList


    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsList()
        }
    }

    fun deleteAllNews(news: NewsArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteAllNews(news)
        }
    }
    fun deleteNews(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.deleteNews(id)
        }
    }
}