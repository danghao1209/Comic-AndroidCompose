package com.app.comicapp.data.apis.user

import com.app.comicapp.data.entities.Chapter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

interface ChapterApi {

    /**
     * example: https://jsonplaceholder.typicode.com/posts
     */
    @GET("/chapter/{id}")
    suspend fun getChapter(@Path("id") id: String): Response<Chapter>

    @POST("/chapter/like")
    suspend fun likeChapter(@Path("id") id: String, @Header("Authorization")token :String): Response<Chapter>
}