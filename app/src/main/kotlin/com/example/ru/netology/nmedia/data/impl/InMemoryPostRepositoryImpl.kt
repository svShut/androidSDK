package com.example.ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ru.netology.nmedia.data.PostRepository
import com.example.ru.netology.nmedia.ui.Post

class InMemoryPostRepositoryImpl: PostRepository {
    private var posts = listOf(
       Post(
           id = 1,
           textHeader = "Netology. University of Internet Professions",
           textDate = "May 21 at 18:36",
           text = "Hello, this is the new Netology! Once upon a time, Netology began with online marketing intensives. Then there were courses on design, development, analytics and management. We grow ourselves and help students grow: from beginners to confident professionals. But the most important thing remains with us: we believe that everyone has the power that makes you want more, aim higher, run faster. Our mission is to help you get on the path to growth and start the chain of change --",
           likeAmount = 1,
           repostAmount = 990,
           viewsAmount = 10000
       ),
       Post(
           id = 2,
           textHeader = "Netology. University of Internet Professions",
           textDate = "May 21 at 18:36",
           text = "Hello, this is the new Netology! Once upon a time, Netology began with online marketing intensives. Then there were courses on design, development, analytics and management. We grow ourselves and help students grow: from beginners to confident professionals. But the most important thing remains with us: we believe that everyone has the power that makes you want more, aim higher, run faster. Our mission is to help you get on the path to growth and start the chain of change --",
           likeAmount = 5,
           repostAmount = 1990,
           viewsAmount = 5000
       )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data


    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id.toLong() != id)it else it.copy(byLikedMe = !it.byLikedMe)
        }
        data.value = posts
    }


    override fun repostById(id: Long) {
        posts = posts.map {
            if (it.id.toLong() != id)it else it.copy(repostAmount = it.repostAmount + 1)
        }
    }


}