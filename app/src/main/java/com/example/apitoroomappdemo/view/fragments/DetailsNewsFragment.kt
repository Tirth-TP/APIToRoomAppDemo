package com.example.apitoroomappdemo.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.apitoroomappdemo.data.model.local.NewsArticle
import com.example.apitoroomappdemo.databinding.FragmentDetailsNewsBinding
import com.example.apitoroomappdemo.utils.glideImageLoader
import com.example.apitoroomappdemo.viewmodel.NewsViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsNewsFragment : Fragment() {

    private var _binding: FragmentDetailsNewsBinding? = null
    private val binding get() = _binding!!

    private var article: NewsArticle? = null
    private val newsViewModel: NewsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentDetailsNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val json = arguments?.getString("news")
        article = Gson().fromJson(json, NewsArticle::class.java)
        article?.let {
            binding.textNewsTitle.text = it.title
            binding.textDate.text = it.publishedAt
            binding.textNewsDescription.text = it.description
            binding.textNewsContent.text = it.content
            binding.textNewsAuthor.text = it.author
            glideImageLoader(requireContext(), it.urlToImage, binding.imageNewsPoster, 1)
        }

        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.imageDelete.setOnClickListener {
            showDeleteConfirmationDialog {
                newsViewModel.deleteNews(article!!.id)
                findNavController().popBackStack()
            }
        }
    }

    private fun showDeleteConfirmationDialog(onDeleteConfirmed: () -> Unit) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setTitle("Delete Confirmation")
            setMessage("Are you sure you want to delete this?")
            setPositiveButton("Delete") { _, _ ->
                onDeleteConfirmed.invoke()
            }
            setNegativeButton("Cancel", null)
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}