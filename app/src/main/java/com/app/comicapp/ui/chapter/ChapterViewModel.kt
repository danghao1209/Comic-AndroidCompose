package com.app.comicapp.ui.chapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.ChapterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChapterViewModel @Inject constructor(private val chapterRepository: ChapterRepository, savedStateHandle: SavedStateHandle,):
    BaseViewModel(){

    private val _chapter = MutableLiveData<Chapter?>()
    val chapter: LiveData<Chapter?> get() = _chapter

    fun fetchData(chapterId: String?) {
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val newChapter = chapterRepository.getChapter(chapterId.toString())

            _chapter.postValue(newChapter)
            isLoading.postValue(false)
        }
    }
}