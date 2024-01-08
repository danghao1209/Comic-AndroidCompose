package com.app.comicapp.data.apis.user

import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.Comic
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.ComicHome
import com.app.comicapp.data.entities.ComicOne
import com.app.comicapp.data.entities.ListSubComic
import com.app.comicapp.data.entities.Subscribe
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ComicApi {

    @GET("/comics/{id}")
    suspend fun getCommicById(@Path("id") id: String, @Header("Authorization") token :String): Response<ComicOne>

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

    @FormUrlEncoded
    @POST("/comics/subscribe")
    suspend fun subscribeComic(@Header("Authorization")token :String, @Field("comicId") comicId: String ): Response<Subscribe>

    @GET("/comics/get/getsub")
    suspend fun getListSub(@Header("Authorization") token :String): Response<ListSubComic>
}