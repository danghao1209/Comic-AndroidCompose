package com.app.comicapp.data.entities

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json

data class User @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "_id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "name") val name: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "coin") val coin: Int,
    @Json(name = "follow") val follow: List<String>,
    @Json(name = "subscribe") val subscribe: List<String>,
    @Json(name = "vip") val vip: Boolean,
    @Json(name = "avatar") val avatar: String,
    @Json(name = "isActive") val isActive: Boolean,
    @Json(name = "accessToken") val accessToken:String,
    @Json(name = "refreshToken") val refreshToken:String
)


data class InfoUser @RequiresApi(Build.VERSION_CODES.O) constructor(
    @Json(name = "_id") val id: String,
    @Json(name = "username") val username: String,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String,
    @Json(name = "coin") val coin: Int,
    @Json(name = "follow") val follow: List<String>,
    @Json(name = "subscribe") val subscribe: List<String>,
    @Json(name = "vip") val vip: Boolean,
    @Json(name = "avatar") val avatar: String,
    @Json(name = "isActive") val isActive: Boolean,
)