package com.app.comicapp.data.repositories

import android.util.Log
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.Comic
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.ComicHome
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

    suspend fun getComic(id:String): ComicAll? = withContext(dispatcher) {
        comicService.getComic(id)
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


}