package com.example.uaenewsapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uaenewsapp.data.beans.Article
import com.example.uaenewsapp.databinding.ItemLayoutBinding

class ArticlesAdapter :
    ListAdapter<Article, ArticlesAdapter.ArticlesHolder>(ArticleComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesHolder {

        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticlesHolder(binding)
    }



    override fun onBindViewHolder(holder: ArticlesHolder, position: Int) {

        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }


    }

    class ArticlesHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(article: Article) {
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .into(binding.imageView)

                binding.titleTextView.text = article.title
                binding.authorTextView.text = article.author
            }
        }

    }


    class ArticleComparator : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Article, newItem: Article) =
            oldItem == newItem
    }

}