package com.app.comicapp.ui.original

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor( val comicRepository: ComicRepository):BaseViewModel(){
    private val _comic = MutableLiveData<List<ComicAll>?>()
    val comic: LiveData<List<ComicAll>?> get() = _comic

    init {
        if (comicRepository == null) {
            throw IllegalStateException("Required value was null.")
        }
    }


    fun fetchData(genre:String){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val trending = comicRepository.getByGenre(genre)
            _comic.value =(trending)
        }
        registerEventParentJobFinish()
    }
}