package com.example.ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import com.example.ru.netology.nmedia.ui.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>

    fun likeById(id: Long)

    fun repostById(id: Long)

    fun save(post: Post)

    fun removeById(id: Long)

}