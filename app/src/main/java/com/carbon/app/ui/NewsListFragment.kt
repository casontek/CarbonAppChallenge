package com.carbon.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.carbon.app.adapter.NewsAdapter
import com.carbon.app.databinding.FragmentNewsListBinding
import com.carbon.app.util.NewsArticleMapper
import com.carbon.app.view.NewsViewModel
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.Constants
import com.carbon.app_common.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class NewsListFragment : Fragment() {
    private var _binding : FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val newsModel : NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        newsModel.viewModelScope.launch {
            if(Constants.isConnected(requireContext())) {
                if (newsModel.getNewsArticles() == null) {
                    newsModel.fetchNewsArticles(getNewsFrom()).collect {
                        when (it.status) {
                            Status.Loading -> toggleUI(View.VISIBLE, View.GONE)
                            Status.Success -> {
                                toggleUI(View.GONE, View.GONE)
                                displayNewsData(it.data)
                                it.data?.let { it2 -> newsModel.setNewsArticles(it2) }
                            }
                            Status.Error -> {
                                toggleUI(View.GONE, View.VISIBLE)
                                binding.errorMessage.text = it.message
                            }
                        }
                    }
                }
                else {
                    displayNewsData(newsModel.getNewsArticles())
                }
            }
            else {
                val cached = newsModel.offlineNewsArticles()
                val articles = arrayListOf<NewsArticle>()
                cached.forEach { it2 ->articles.add(NewsArticleMapper.getNewsArticle(it2)) }

                if(!articles.isNullOrEmpty()){
                    displayNewsData(articles)
                }
                else {
                    toggleUI(View.GONE, View.VISIBLE)
                    binding.errorMessage.text = "no internet. check and try-again!"
                }
            }
        }
    }

    private fun displayNewsData(data: List<NewsArticle>?) {
        if(data != null) {
            binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerview.adapter = NewsAdapter(requireContext(), data)
        }
    }

    private fun toggleUI(progressState: Int, messageState: Int) {
        binding.loadingProgrss.visibility = progressState
        binding.errorContainer.visibility = messageState
    }

    private fun getNewsFrom() : String {
        val c = Calendar.getInstance()
        c.time = Date()
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val year = c.get(Calendar.YEAR)
        val m = if(month < 10) "0$month" else "$month"
        val d = if(day < 10) "0$day" else "$day"
        return "$year-$m-$d"
    }

}