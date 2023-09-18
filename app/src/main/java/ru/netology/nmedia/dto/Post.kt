package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean = false,
    val likeCounter:  Int = 0,
    val shareCounter: Int = 0,
    val views: Int = 999
)