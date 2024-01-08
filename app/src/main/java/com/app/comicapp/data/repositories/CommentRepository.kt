package com.app.comicapp.data.repositories

import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.CommentsResponse
import com.app.comicapp.data.entities.ListComment
import com.app.comicapp.data.services.ComicService
import com.app.comicapp.data.services.CommentService
import com.app.comicapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentRepository @Inject constructor(
    private val commentService: CommentService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getComments(id:String, token: String): CommentsResponse? = withContext(dispatcher) {
        commentService.getComments(id, token)
    }
    suspend fun comment(token: String,comicId:String,content: String): CommentsResponse? = withContext(dispatcher) {
        commentService.comment(token, comicId, content)
    }
    suspend fun getListComment(token: String): ListComment? = withContext(dispatcher) {
        commentService.getListComment(token)
    }
}