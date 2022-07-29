package com.example.ru.netology.nmedia.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ru.netology.nmedia.data.PostRepository
import com.example.ru.netology.nmedia.data.impl.InMemoryPostRepositoryImpl
import com.example.ru.netology.nmedia.ui.Post

private val empty = Post(
    0,
    "",
    "",
    "",
    0,
    0,
    0,
    false
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepositoryImpl()

    val data  = repository.getAll()
    val edited = MutableLiveData(empty)
    fun onLikeClicked(id: Long) = repository.likeById(id)
    fun onRepostClicked(id: Long) = repository.repostById(id)
    fun onRemoveClicked(id: Long) = repository.removeById(id)

    fun save() {
        edited.value?.let {
            repository.save(it)

            edited.value = empty
        }
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        if (content.isBlank()) {
            return
        }

        edited.value?.let {
           edited.value = it.copy(text = content)
        }
    }

}