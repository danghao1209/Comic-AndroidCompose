package com.app.comicapp.data.services

import com.app.comicapp.data.apis.user.ChapterApi
import com.app.comicapp.data.apis.user.UserApi
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.User
import javax.inject.Inject

class UserService @Inject constructor(private val userApi: UserApi) {

    suspend fun login(username:String, password:String): User? {

        val response = userApi.signIn(username,password)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
    suspend fun register(username:String, password:String, name:String, email:String): User? {


        val response = userApi.signUp(username,password, name,email)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun subcribe(comicId:String): User? {


        val response = userApi.subscribe(comicId)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun getInfo(token:String): User? {


        val response = userApi.getInfoUser(token)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
}