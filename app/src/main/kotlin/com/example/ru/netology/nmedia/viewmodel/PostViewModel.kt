package com.example.ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ru.netology.nmedia.data.PostRepository
import com.example.ru.netology.nmedia.data.impl.InMemoryPostRepositoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepositoryImpl()

    val data  = repository.getAll()

    fun onLikeClicked(id: Long) = repository.likeById(id)

    fun onRepostClicked(id: Long) = repository.repostById(id)

}