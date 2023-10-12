package com.app.comicapp.data.repositories

import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.Comic
import com.app.comicapp.data.services.ChapterService
import com.app.comicapp.data.services.ComicService
import com.app.comicapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChapterRepository @Inject constructor(
    private val chapterService: ChapterService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getChapter(id:String): Chapter? = withContext(dispatcher) {
        chapterService.getChapter(id)
    }

    suspend fun likeChapter(id:String, token:String): Chapter? = withContext(dispatcher) {
        chapterService.likeChapter(id,token)
    }
}