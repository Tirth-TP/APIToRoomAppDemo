package com.example.apitoroomappdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apitoroomappdemo.data.model.local.NewsArticle
import com.example.apitoroomappdemo.databinding.ItemNewsListBinding
import com.example.apitoroomappdemo.utils.glideImageLoader

/**
 * Created by Tirth Patel.
 */

class NewsListAdapterAdapter(
    private val context: Context,
    private var mList: List<NewsArticle>?,
    private val postListClickFunction: (NewsArticle) -> Unit,
) : RecyclerView.Adapter<NewsListAdapterAdapter.NewsListAdapterAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NewsListAdapterAdapterViewHolder {
        val view = ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsListAdapterAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    override fun onBindViewHolder(holder: NewsListAdapterAdapterViewHolder, position: Int) {
        mList?.let {
            holder.bind(it[position])
        }
    }

    inner class NewsListAdapterAdapterViewHolder(
        private val binding: ItemNewsListBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: NewsArticle) {
            binding.txtTitle.text = article.title
            binding.txtAuthor.text = article.author
            binding.txtPublishedOn.text = article.publishedAt
            glideImageLoader(context, article.urlToImage, binding.imgPoster, 32)
            binding.root.setOnClickListener {
                postListClickFunction(article)
            }
        }
    }
}
