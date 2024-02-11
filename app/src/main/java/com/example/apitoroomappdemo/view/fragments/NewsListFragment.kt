package com.example.apitoroomappdemo.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apitoroomappdemo.R
import com.example.apitoroomappdemo.adapter.NewsListAdapterAdapter
import com.example.apitoroomappdemo.data.model.local.NewsArticle
import com.example.apitoroomappdemo.data.model.remote.Article
import com.example.apitoroomappdemo.databinding.FragmentNewsListBinding
import com.example.apitoroomappdemo.viewmodel.NewsViewModel
import com.example.apitoroomappdemo.viewmodel.RecyclerViewStateViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notifyAll

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels()

    private var isApiCalled = false
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerViewModel: RecyclerViewStateViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        layoutManager = LinearLayoutManager(requireContext())

        if (!isApiCalled) {
            newsViewModel.getNews()
            isApiCalled = true
        }

        val rvNewsList = binding.rvNewsList
        rvNewsList.layoutManager = layoutManager

        recyclerViewModel = ViewModelProvider(this)[RecyclerViewStateViewModel::class.java]

        recyclerViewModel.scrollPosition.observe(viewLifecycleOwner) { position ->
            layoutManager.scrollToPositionWithOffset(position, 0)
        }

        newsViewModel.newsList.observe(viewLifecycleOwner) { data ->
            data?.let {
                val adapter = NewsListAdapterAdapter(requireContext(), it, ::onNewsClick)
                rvNewsList.adapter = adapter

            }
        }
    }

    private fun onNewsClick(article: NewsArticle) {
        val bundle = Bundle()
        bundle.putString("news", Gson().toJson(article))
        findNavController().navigate(
            R.id.action_newsListFragment_to_detailsNewsFragment, bundle
        )
    }

    override fun onPause() {
        super.onPause()
        val position = layoutManager.findFirstVisibleItemPosition()
        recyclerViewModel.saveScrollPosition(position)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}