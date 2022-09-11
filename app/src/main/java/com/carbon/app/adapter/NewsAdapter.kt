package com.carbon.app.adapter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carbon.app.R
import com.carbon.app.databinding.NewsInflatorBinding
import com.carbon.app_common.model.NewsArticle
import com.carbon.app_common.utils.DateConverter

class NewsAdapter(
    val c: Context,
    val articles: List<NewsArticle>
) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    class NewsHolder(val binding: NewsInflatorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder =
        NewsHolder(
            NewsInflatorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = articles[position]
        holder.binding.title.text = article.title
        holder.binding.dateSource.text = "${DateConverter.getConvertedDate(article.publishedAt)}"
        Glide.with(c).load(article.urlToImage).into(holder.binding.timelineMedia)

        holder.itemView.setOnClickListener {
            Log.d("_&article", "data: $article")
            val bundle = Bundle()
            bundle.putParcelable("article", article)
            Navigation.findNavController(holder.itemView).navigate(
                R.id.action_newsListFragment_to_newsDetailFragment,
                bundle
            )
        }
    }

    override fun getItemCount(): Int = articles.size

}