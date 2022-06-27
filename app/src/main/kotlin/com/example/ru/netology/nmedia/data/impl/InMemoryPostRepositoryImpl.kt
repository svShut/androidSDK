package com.example.ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import com.example.ru.netology.nmedia.ui.Post
import com.example.ru.netology.nmedia.data.PostRepository

class InMemoryPostRepositoryImpl: PostRepository {
    private val post: Post
        get() = checkNotNull(data.value){
        "data.value == null"
    }

    override val data = MutableLiveData<Post>(
       Post(
           id = 1,
           textHeader = "Netology. University of Internet Professions",
           textDate = "May 21 at 18:36",
           text = "Hello, this is the new Netology! Once upon a time, Netology began with online marketing intensives. Then there were courses on design, development, analytics and management. We grow ourselves and help students grow: from beginners to confident professionals. But the most important thing remains with us: we believe that everyone has the power that makes you want more, aim higher, run faster. Our mission is to help you get on the path to growth and start the chain of change --",
           likeAmount = 999,
           repostAmount = 990,
           viewsAmount = 10000
       )
    )


    override fun like() {
        val post = post
        data.value = post.copy(
            likeAmount = if (post.byLikedMe) post.likeAmount - 1 else post.likeAmount + 1,
            byLikedMe = !post.byLikedMe
        )
    }

    override fun repost() {
        val post = post
        data.value = post.copy(
            repostAmount = post.repostAmount + 1
        )
    }
}