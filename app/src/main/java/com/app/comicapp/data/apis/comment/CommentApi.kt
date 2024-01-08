package com.app.comicapp.data.apis.comment

import com.app.comicapp.data.entities.CommentsResponse
import com.app.comicapp.data.entities.ListComment
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface CommentApi {
    @GET("/comment/comic/{id}")
    suspend fun getComments(@Path("id") id: String,@Header("Authorization")token :String,): Response<CommentsResponse>

    @GET("/comment/getlistcomments")
    suspend fun getListComment(@Header("Authorization")token :String,): Response<ListComment>
    @FormUrlEncoded
    @POST("/comment")
    suspend fun comment(
        @Header("Authorization")token :String,
        @Field("comicId") comicId: String,
        @Field("content") content: String
    ): Response<CommentsResponse>
}