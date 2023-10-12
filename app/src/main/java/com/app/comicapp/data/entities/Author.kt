package com.app.comicapp.data.entities

data class Author(
    val _id: String,
    val name: String,
    val series: List<String>,
    val followers: List<String>,
    val avata: String
)