package com.example.ru.netology.nmedia.ui

data class Post (
    val id: Int,
    val textHeader: String,
    val textDate: String,
    val text: String,
    val likeAmount: Int,
    val repostAmount: Int,
    val viewsAmount: Int,
    var byLikedMe: Boolean = false

)