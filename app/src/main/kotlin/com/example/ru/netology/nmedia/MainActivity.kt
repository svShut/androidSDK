package com.example.ru.netology.nmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var likeAmount = 999
    private var viewsAmount = 1000
    private var repostViews = 990

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            textHeader = "Netology. University of Internet Professions",
            textDate = "May 21 at 18:36",
            text = "Hello, this is the new Netology! Once upon a time, Netology began with online marketing intensives. Then there were courses on design, development, analytics and management. We grow ourselves and help students grow: from beginners to confident professionals. But the most important thing remains with us: we believe that everyone has the power that makes you want more, aim higher, run faster. Our mission is to help you get on the path to growth and start the chain of change --",
            byLikedMe = false
        )

        with(binding) {
            textHeader.text = post.textHeader
            textDate.text = post.textDate
            postText.text = post.text
            amountRepost.text = repostViews.toString()
            amountLike.text = likeAmount.toString()
            amountViews.text = viewsAmount.toString()
            if (post.byLikedMe) {
                buttonLike.setImageResource(R.drawable.like)
            }
            buttonRepost.setOnClickListener {
                buttonRepost.setImageResource(R.drawable.repost)
                repostViews++
                amountRepost.text = сounter(repostViews).toString()
            }

            buttonLike.setOnClickListener {
                post.byLikedMe = !post.byLikedMe
                buttonLike.setImageResource(
                    if (post.byLikedMe) R.drawable.like else R.drawable.cry_like
                )

                var likes = likeAmount
                amountLike.text = likeAmount.toString()

                if (!post.byLikedMe) {
                    likes++
                    amountLike.text = сounter(likes).toString()
                }

                amountViews.text = сounter(viewsAmount).toString()
            }

        }

    }


    private fun сounter(value: Int): Any {
        var amount = ""
        when (value) {
            in 1000..1099 -> amount = "1K"
        }
        return if (value < 1000) value else amount
    }
}