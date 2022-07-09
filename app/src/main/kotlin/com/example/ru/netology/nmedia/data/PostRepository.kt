package com.example.ru.netology.nmedia.data

import androidx.lifecycle.LiveData
import com.example.ru.netology.nmedia.ui.Post

interface PostRepository {
    val data: LiveData<Post>

    fun like()

    fun repost()
}