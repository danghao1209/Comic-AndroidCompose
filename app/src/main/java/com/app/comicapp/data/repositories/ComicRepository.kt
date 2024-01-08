package com.app.comicapp.data.repositories

import com.app.comicapp.data.database.entities.UserToken
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.ComicOne
import com.app.comicapp.data.entities.ListSubComic
import com.app.comicapp.data.entities.Subscribe
import com.app.comicapp.data.services.ComicService
import com.app.comicapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ComicRepository @Inject constructor(
    private val comicService: ComicService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getComic(id:String, token:String): ComicOne? = withContext(dispatcher) {
        comicService.getComic(id, token)
    }


    suspend fun getComics(): List<ComicAll>? = withContext(dispatcher) {
        comicService.getComics()
    }

    suspend fun getListChapterById(id:String): List<Chapter>? = withContext(dispatcher) {
        comicService.getListChapterById(id)
    }

    suspend fun getTopSeries(): List<ComicAll>? = withContext(dispatcher) {
        comicService.getTopSeries()
    }

    suspend fun getByGenre(id: String): List<ComicAll>? = withContext(dispatcher) {
        comicService.getByGenre(id)
    }

    suspend fun getNewarrvals(): List<ComicAll>? = withContext(dispatcher) {
        comicService.getNewarrvals()
    }

    suspend fun getTrending(): List<ComicAll>? = withContext(dispatcher) {
        comicService.getTrending()
    }

    suspend fun search(id:String): List<ComicAll>? = withContext(dispatcher) {
        comicService.search(id)
    }

    suspend fun subscribe(token: String, comicId:String): Subscribe? = withContext(dispatcher) {
        comicService.subscribe(token, comicId)
    }

    suspend fun getListSub(token: String): ListSubComic? = withContext(dispatcher) {
        comicService.getListSub(token)
    }
}