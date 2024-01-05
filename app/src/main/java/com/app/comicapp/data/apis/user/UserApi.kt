package com.app.comicapp.data.apis.user

import com.app.comicapp.data.entities.User
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    /**
     * example: https://jsonplaceholder.typicode.com/posts
     */
    @GET("/users")
    suspend fun getInfoUser(@Header("Authorization")token :String): Response<User>

    @FormUrlEncoded
    @POST("/auth/signin")
    suspend fun signIn(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<User>

    @FormUrlEncoded
    @POST("/auth/signup")
    suspend fun signUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("rePassword") rePassword: String,
        @Field("name") name: String,
        @Field("email") email: String,
    ): Response<User>

    @FormUrlEncoded
    @POST("/auth/changepassword")
    suspend fun changePass(
        @Field("oldPass") oldPass: String,
        @Field("newPass") newPass: String,
        @Field("confirmNewPass") confirmNewPass: String,
        @Header("Authorization")token :String
    ): Response<User>

    @FormUrlEncoded
    @POST("/subscribe")
    suspend fun subscribe(
        @Field("comicId") comicId: String,
    ): Response<User>

}