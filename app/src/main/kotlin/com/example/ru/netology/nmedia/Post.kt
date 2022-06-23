package com.example.ru.netology.nmedia

data class Post (
    val id: Int,
    val textHeader: String,
    val textDate: String,
    val text: String,
    var byLikedMe: Boolean

)