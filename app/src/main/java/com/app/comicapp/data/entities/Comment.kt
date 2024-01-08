package com.app.comicapp.data.entities

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommentsResponse @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "comments") val comments: List<Comment>
)

@JsonClass(generateAdapter = true)
data class Comment @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "name") val name: String,
    @Json(name = "avatar") val avatar: String,
    @Json(name = "content") val content: String,
    @Json(name = "commentId") val commentId: String
)

@JsonClass(generateAdapter = true)
data class ListComment @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "data") val data: List<CommentItem>
)

@JsonClass(generateAdapter = true)
data class CommentItem @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "comicId") val comicId: String,
    @Json(name = "title") val title: String,
    @Json(name = "thumbImg") val thumbImg: String,
    @Json(name = "content") val content: String,
    @Json(name = "commentId") val commentId: String,
)