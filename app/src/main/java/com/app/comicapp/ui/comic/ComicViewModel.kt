package com.app.comicapp.ui.comic

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.Comic
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.ComicRepository
import com.app.comicapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val comicRepository: ComicRepository,private val userRepository: UserRepository, savedStateHandle: SavedStateHandle,):BaseViewModel(){
    private val _comic = MutableLiveData<ComicAll?>()
    val comic: LiveData<ComicAll?> get() = _comic


    private val _listChapter = MutableLiveData<List<Chapter>?>()
    val listChapter: LiveData<List<Chapter>?> get() = _listChapter


    private val _currentChaper = MutableLiveData<String?>()
    val currentChaper: LiveData<String?> get() = _currentChaper

    private val _nextChapter = MutableLiveData<String?>()
    val nextChapter: LiveData<String?> get() = _nextChapter

    private val _backChapter = MutableLiveData<String?>()
    val backChapter: LiveData<String?> get() = _backChapter

    fun fetchData(comicId:String?){
        Log.e("comic", comicId.toString())
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val newComic = comicRepository.getComic(comicId.toString())

            _comic.postValue(newComic)
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


    fun subcribe(){
        if(comic.value != null){
            parentJob = viewModelScope.launch(exceptionHandler){
                isLoading.postValue(true)
                userRepository.subcribe(comic.value!!.id)

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