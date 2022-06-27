package com.example.ru.netology.nmedia.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.ru.netology.nmedia.R
import com.example.ru.netology.nmedia.databinding.ActivityMainBinding
import com.example.ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel by viewModels<PostViewModel>()
        viewModel.data.observe(this) { post ->


            with(binding) {
                textHeader.text = post.textHeader
                textDate.text = post.textDate
                postText.text = post.text
                amountLike.text = counter(post.likeAmount).toString()
                amountRepost.text = counter(post.repostAmount).toString()
                amountViews.text = counter(post.viewsAmount).toString()

//                buttonRepost.setOnClickListener {
//                    buttonRepost.setImageResource(R.drawable.repost)
////                    post.repostAmount++
//                    amountRepost.text = counter(post.repostAmount).toString()
//                }

                buttonLike.setImageResource(
                    if (post.byLikedMe) R.drawable.cry_like else R.drawable.like
                )


                buttonLike.setOnClickListener {
                    viewModel.onLikeClicked()
                }

                buttonRepost.setOnClickListener {
                    viewModel.onRepostClicked()
                }

            }
        }
    }

    private fun counter(value: Int): Any {
        var amount = ""
        when (value) {
            in 1_000..1_099 -> amount = "1K"
            in 10_000..10_999 -> amount = "10K"
            in 100_000..100_999 -> amount = "100K"
            in 1_000_000..1_099_999 -> amount = "1M"
        }
        return if (value < 1_000) value else amount
    }

}