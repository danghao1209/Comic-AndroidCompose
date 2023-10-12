package com.app.comicapp.data.services

import com.app.comicapp.data.apis.user.ChapterApi
import com.app.comicapp.data.entities.Chapter
import javax.inject.Inject

class ChapterService @Inject constructor(private  val chapterApi: ChapterApi) {

    suspend fun getChapter(id:String): Chapter? {


        val response = chapterApi.getChapter(id)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
    suspend fun likeChapter(id:String, token:String): Chapter? {


        val response = chapterApi.likeChapter(id, token)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
}