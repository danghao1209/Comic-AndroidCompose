package com.app.comicapp.data.services

import com.app.comicapp.data.apis.comment.CommentApi
import com.app.comicapp.data.entities.CommentsResponse
import com.app.comicapp.data.entities.ListComment
import javax.inject.Inject

class CommentService @Inject constructor(private  val commentApi: CommentApi) {

    suspend fun getComments(id: String, token: String): CommentsResponse? {
        val response = commentApi.getComments(id, token)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun comment(token: String, comicId:String, content:String): CommentsResponse? {

        val response = commentApi.comment(token, comicId, content)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun getListComment(token: String): ListComment? {

        val response = commentApi.getListComment(token)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
}