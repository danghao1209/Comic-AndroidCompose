package com.app.comicapp.data.entities

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json

data class Chapter @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "_id") var id: String,
    @Json(name = "image") var image: List<String>,
    @Json(name = "chapterNumber") var chapterNumber: Int,
    @Json(name = "titleChapter") var titleChapter: String,
    @Json(name = "thumbChapter") var thumbChapter: String,
    @Json(name = "like") var like: List<String>,
    @Json(name = "view") var view: Int
)