package com.app.comicapp.data.apis.user

import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.Comic
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.ComicHome
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ComicApi {

    /**
     * example: https://jsonplaceholder.typicode.com/posts
     */
    @GET("/comics/{id}")
    suspend fun getCommicById(@Path("id") id: String,): Response<ComicAll>

    @GET("/comics/listchapter/{id}")
    suspend fun getListChapterById(@Path("id") id: String,): Response<List<Chapter>>
    @GET("/comics/get/all")
    suspend fun getAllCommic(): Response<List<ComicAll>>


    @GET("/comics/get/topseries")
    suspend fun getTopSeries(): Response<List<ComicAll>>

    @GET("/comics/get/trending")
    suspend fun getTrending(): Response<List<ComicAll>>

    @GET("/comics/get/newarrvals")
    suspend fun getNewarrvals(): Response<List<ComicAll>>


    @GET("/comics/search/genre/{id}")
    suspend fun getByGenre(@Path("id") id: String,): Response<List<ComicAll>>


    @GET("/comics/search/param/{id}")
    suspend fun search(@Path("id") id: String,): Response<List<ComicAll>>
    @POST("/comics/subscribe")
    suspend fun subscribeComic(@Header("Authorization")token :String): Response<Comic>
}