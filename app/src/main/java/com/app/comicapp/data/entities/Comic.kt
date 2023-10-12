package com.app.comicapp.data.entities

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json

data class Comic @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "_id")val id: String,
    @Json(name = "title")val title: String,
    @Json(name = "description")val description: String,
    @Json(name = "thumbImg")val thumbImg: String,
    @Json(name = "previewImg")val previewImg: List<String?>,
    @Json(name = "chapters")val chapters: List<String?>,
    @Json(name = "genre")val genre: String,
    @Json(name = "comment")val comment: List<String?>,
    @Json(name = "totalViews")val totalViews: Number,
    @Json(name = "totalRate")val totalRate: Number,
)

data class ComicHome(
    @Json(name = "trending") val trending: List<ComicAll>,
    @Json(name = "newArrvals") val newArrvals: List<ComicAll>,
    @Json(name = "topSeries") val topSeries: List<ComicAll>,
    @Json(name = "action") val action: List<ComicAll>,
)


data class ComicAll @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "_id") var id: String,
    @Json(name = "title" ) var title: String,
    @Json(name = "description") var description: String,
    @Json(name = "thumbImg") var thumbImg: String,
    @Json(name = "previewImg") var previewImg: List<String>,
    @Json(name = "chapters") var chapters: List<String>,
    @Json(name = "rate") var rate: Any,
    @Json(name = "genre") var genre: String,
    @Json(name = "comment") var comment: Any,
    //@Json(name = "comment") var comment: Any,
)

