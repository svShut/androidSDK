package com.example.ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            textHeader = "Netology. University of Internet Professions",
            textDate = "May 21 at 18:36",
            text = "Hello, this is the new Netology! Once upon a time, Netology began with online marketing intensives. Then there were courses on design, development, analytics and management. We grow ourselves and help students grow: from beginners to confident professionals. But the most important thing remains with us: we believe that everyone has the power that makes you want more, aim higher, run faster. Our mission is to help you get on the path to growth and start the chain of change --",
            likeAmount = 999,
            repostAmount = 990,
            viewsAmount = 10000,
            byLikedMe = false
        )

        with(binding) {
            textHeader.text = post.textHeader
            textDate.text = post.textDate
            postText.text = post.text
            amountLike.text = post.likeAmount.toString()
            amountRepost.text = post.repostAmount.toString()
            amountViews.text = сounter(post.viewsAmount).toString()

            if (post.byLikedMe) {
                buttonLike.setImageResource(R.drawable.like)
            }
            buttonRepost.setOnClickListener {
                buttonRepost.setImageResource(R.drawable.repost)
                post.repostAmount++
                amountRepost.text = сounter(post.repostAmount).toString()
            }

            buttonLike.setOnClickListener {
                post.byLikedMe = !post.byLikedMe
                buttonLike.setImageResource(
                    if (post.byLikedMe) R.drawable.like else R.drawable.cry_like
                )

                amountLike.text = сounter(post.likeAmount).toString()

                var likes = post.likeAmount
                if (!post.byLikedMe) {
                    likes++
                    amountLike.text = сounter(likes).toString()
                }

            }

        }

    }


     fun сounter(value: Int): Any {
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