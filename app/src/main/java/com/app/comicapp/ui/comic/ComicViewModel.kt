package com.app.comicapp.ui.comic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.ComicOne
import com.app.comicapp.data.entities.CommentsResponse
import com.app.comicapp.data.repositories.ComicRepository
import com.app.comicapp.data.repositories.CommentRepository
import com.app.comicapp.data.repositories.LocalRepository
import com.app.comicapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val comicRepository: ComicRepository, private val userRepository: UserRepository, private val commentRepository: CommentRepository, private val localRepository: LocalRepository, savedStateHandle: SavedStateHandle,):BaseViewModel(){
    private val _comic = MutableLiveData<ComicOne?>()
    val comic: LiveData<ComicOne?> get() = _comic


    private val _listChapter = MutableLiveData<List<Chapter>?>()
    val listChapter: LiveData<List<Chapter>?> get() = _listChapter


    private val _currentChaper = MutableLiveData<String?>()
    val currentChaper: LiveData<String?> get() = _currentChaper

    private val _nextChapter = MutableLiveData<String?>()
    val nextChapter: LiveData<String?> get() = _nextChapter

    private val _backChapter = MutableLiveData<String?>()
    val backChapter: LiveData<String?> get() = _backChapter

    private val _isSub = MutableLiveData<Boolean?>()
    val isSub: LiveData<Boolean?> get() = _isSub

    private val _commentText = MutableLiveData<String?>()
    val commentText: LiveData<String?> get() = _commentText

    private val _listComments = MutableLiveData<CommentsResponse?>()
    val listComments: LiveData<CommentsResponse?> get() = _listComments
    fun onCommentTextChanged(text: String) {
        _commentText.postValue( text)
        Log.e("comment", commentText.value.toString())
    }

    fun fetchData(comicId:String?){
        Log.e("comic", comicId.toString())
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val token = localRepository.getToken()
            val newComic = token?.let { comicRepository.getComic(comicId.toString(), it.token) }
            _comic.postValue(newComic)
            if (newComic != null) {
                _isSub.postValue(newComic.isSub)
            };
            val listComments = token?.let { commentRepository.getComments(comicId.toString(), it.token) }
            _listComments.postValue(listComments)
            isLoading.postValue(false)
        }
    }

    fun fetchListChapter(comicId:String?){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val newListChapter = comicRepository.getListChapterById(comicId.toString())

            _listChapter.postValue(newListChapter)
            isLoading.postValue(false)
        }
    }
    fun sendComment(){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val token = localRepository.getToken()
            val newListChapter =
                token?.let { comic.value?.let { it1 -> _commentText.value?.let { it2 ->
                    commentRepository.comment(it.token, it1.id,
                        it2
                    )
                } } }

            _commentText.postValue("")
            _listComments.postValue(newListChapter)
            isLoading.postValue(false)
        }
    }

    fun subscribe(){
        if(comic.value != null){
            parentJob = viewModelScope.launch(exceptionHandler){
                isLoading.postValue(true)
                val token = localRepository.getToken()
                if (token != null) {
                    val newComic = comicRepository.subscribe(token.token, comic.value!!.id)
                    if (newComic != null) {
                        _isSub.postValue(newComic.isSub)
                    }
                }
                isLoading.postValue(false)
            }
        }
    }

    fun setChapter(chapterId:String?){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            var currentindex = comic.value!!.chapters.indexOf(chapterId)

            _currentChaper.value =(chapterId)
            if(currentindex == 0){
                _nextChapter.value = comic.value!!.chapters[1]
                _backChapter.value = comic.value!!.chapters[0]
            }
            else if(currentindex == comic.value!!.chapters.size ){
                _nextChapter.value =(comic.value!!.chapters[comic.value!!.chapters.size-1])
                _backChapter.value =(comic.value!!.chapters[comic.value!!.chapters.size -2])
            }
            else{
                _nextChapter.value =(comic.value!!.chapters[currentindex +1])
                _backChapter.value =(comic.value!!.chapters[currentindex -1])
            }
            Log.e("id", currentindex.toString() )
            Log.e("id1", currentChaper.value.toString() )
            Log.e("id2", nextChapter.value.toString() )
            Log.e("id3", backChapter.value.toString() )
            Log.e("id4", comic.value!!.chapters[1] )

            isLoading.postValue(false)
        }
    }


}