package com.app.comicapp.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val comicRepository: ComicRepository, savedStateHandle: SavedStateHandle,): BaseViewModel(){

    var comic = MutableLiveData<List<ComicAll>?>()
        private set

    val comicList: MutableLiveData<List<ComicAll>?> get() = comic
    fun searchData(data: String) {
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val newComic = comicRepository.search(data)
            comic.value=newComic
        }
        registerEventParentJobFinish()
    }
}