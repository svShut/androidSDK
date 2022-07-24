package com.example.ru.netology.nmedia.data.impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ru.netology.nmedia.R
import com.example.ru.netology.nmedia.databinding.CardPostBinding
import com.example.ru.netology.nmedia.ui.Post

typealias OnLikeListener = (post: Post) -> Unit
typealias onRepostListener = (post: Post) -> Unit

class PostsAdapter(
    private val onLikeListener: OnLikeListener,
    private val onRepostListener: onRepostListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener, onRepostListener)
    }


    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener,
    private val onRepostListener: onRepostListener
    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            textHeader.text = post.textHeader
            textDate.text = post.textDate
            postText.text = post.text
            amountLike.text = counter(post.likeAmount).toString()
            amountRepost.text = counter(post.repostAmount).toString()
            amountViews.text = counter(post.viewsAmount).toString()

            buttonRepost.setOnClickListener {
                onRepostListener(post)
            }
            buttonLike.setImageResource(
                if (post.byLikedMe) R.drawable.cry_like else R.drawable.like
            )
            buttonLike.setOnClickListener {
                onLikeListener(post)

            }

        }
    }
}


    private fun counter(value: Int): Any {
        var amount = ""
        when (value) {
            in 1_000..1_999 -> amount = "1K"
            in 2_000..2_999 -> amount = "2k"
            in 3_000..3_999 -> amount = "3k"
            in 4_000..4_999 -> amount = "4k"
            in 5_000..5_999 -> amount = "5k"
            in 6_000..6_999 -> amount = "6k"
            in 7_000..7_999 -> amount = "7k"
            in 8_000..8_999 -> amount = "8k"
            in 9_000..9_999 -> amount = "9k"
            in 10_000..10_999 -> amount = "10K"
            in 100_000..100_999 -> amount = "100K"
            in 1_000_000..1_099_999 -> amount = "1M"
        }
        return if (value < 1_000) value else amount
    }

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}

