package com.app.comicapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.ComicRepository
import com.app.comicapp.data.repositories.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModal @Inject constructor(private val comicRepository: ComicRepository, val localRepository: LocalRepository):
    BaseViewModel(){
    private val _trending = MutableLiveData<List<ComicAll>?>()
    val trending: LiveData<List<ComicAll>?> get() = _trending


    private val _topseries = MutableLiveData<List<ComicAll>?>()
    val topseries: LiveData<List<ComicAll>?> get() = _topseries

    private val _newarrvals = MutableLiveData<List<ComicAll>?>()
    val newarrvals: LiveData<List<ComicAll>?> get() = _newarrvals

    private val _action = MutableLiveData<List<ComicAll>?>()
    val action: LiveData<List<ComicAll>?> get() = _action


    init {
        if (comicRepository == null) {
            throw IllegalStateException("Required value was null.")
        }
        //getToken()
        //fetchData()
    }
    fun fetchData(){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)


            val trending = comicRepository.getTrending()
            _trending.postValue(trending)
            Log.e("Comic", _trending.value.toString())

            val topseries = comicRepository.getTopSeries()
            _topseries.postValue(topseries)
            Log.e("Comic", _topseries.value.toString())


            val newarrvals = comicRepository.getNewarrvals()
            _newarrvals.postValue(newarrvals)
            Log.e("Comic", _newarrvals.value.toString())


            val action = comicRepository.getByGenre("Action")
            _action.postValue(action)
            Log.e("Comic", _action.value.toString())


        }

        registerEventParentJobFinish()
    }





}