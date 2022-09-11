package com.carbon.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.carbon.app.databinding.FragmentNewsDetailBinding
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.DateConverter

class NewsDetailFragment : Fragment() {
    private var _binding : FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBinding.inflate(
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
        binding.backBT.setOnClickListener { activity?.onBackPressed() }
        val article: NewsArticle? = arguments?.getParcelable("article")
        article?.apply {
            binding.newsTitle.text = title
            binding.newsBody1.text = description
            binding.newsBody2.text = content
            binding.newsDate.text = DateConverter.getConvertedDate(publishedAt)
            //display news picture
            Glide.with(requireContext())
                .load(urlToImage)
                .into(binding.newsPhoto)
        }
    }

}