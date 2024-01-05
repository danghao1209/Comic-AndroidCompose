package com.app.comicapp.data.services

import com.app.comicapp.data.apis.user.ChapterApi
import com.app.comicapp.data.apis.user.UserApi
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.User
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
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
    suspend fun register(username:String, password:String, rePassword:String, name:String, email:String): User? {

        fun createPartFromString(value: String): MultipartBody.Part {
            val requestBody = value.toRequestBody("text/plain".toMediaTypeOrNull())
            return MultipartBody.Part.createFormData("email", null, requestBody)
        }
        val response = userApi.signUp(username,password,rePassword , name,email)

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

    suspend fun changePass(oldPass:String, newPass:String, confirmNewPass:String,token:String): User? {


        val response = userApi.changePass(oldPass,newPass,confirmNewPass,token)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
}