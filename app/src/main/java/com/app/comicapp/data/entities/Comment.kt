package com.app.comicapp.data.entities

data class Comment(
    val _id: String,
    val userId: String,
    val content: String,
    val userLikes: List<String>
)