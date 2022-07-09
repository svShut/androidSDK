package com.example.ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ru.netology.nmedia.data.PostRepository
import com.example.ru.netology.nmedia.data.impl.InMemoryPostRepositoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepositoryImpl()

    val data get() = repository.data

    fun onLikeClicked() = repository.like()

    fun onRepostClicked() = repository.repost()

}