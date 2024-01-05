package com.app.comicapp.data.services

import android.util.Log
import com.app.comicapp.data.apis.user.ComicApi
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.Comic
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.ComicHome
import com.app.comicapp.data.entities.ComicOne
import com.app.comicapp.data.entities.ListSubComic
import com.app.comicapp.data.entities.Subscribe
import javax.inject.Inject

class ComicService @Inject constructor(private  val comicApi: ComicApi) {

    suspend fun getComics():  List<ComicAll>? {
        val response = comicApi.getAllCommic()
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }



    suspend fun getComic(id:String, token:String): ComicOne? {

        val response = comicApi.getCommicById(id, token)

        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }


    suspend fun getListChapterById(id:String): List<Chapter>? {

        val response = comicApi.getListChapterById(id)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }


    suspend fun getTopSeries(): List<ComicAll>? {

        val response = comicApi.getTopSeries()
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun getTrending(): List<ComicAll>? {

        val response = comicApi.getTrending()
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun getNewarrvals(): List<ComicAll>? {

        val response = comicApi.getNewarrvals()
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }


    suspend fun getByGenre(id:String): List<ComicAll>? {

        val response = comicApi.getByGenre(id)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun search(id:String): List<ComicAll>? {

        val response = comicApi.search(id)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun subscribe(id:String, token: String): Subscribe? {

        val response = comicApi.subscribeComic(id, token)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }

    suspend fun getListSub( token: String): ListSubComic? {

        val response = comicApi.getListSub(token)
        if(response.isSuccessful){
            return response.body()
        }
        else{

            throw Exception(response.message())
        }
    }
}