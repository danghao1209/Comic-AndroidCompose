package com.app.comicapp.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.entities.ListComment
import com.app.comicapp.data.entities.ListSubComic
import com.app.comicapp.data.repositories.ComicRepository
import com.app.comicapp.data.repositories.CommentRepository
import com.app.comicapp.data.repositories.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val comicRepository: ComicRepository, private val commentRepository: CommentRepository, private val localRepository: LocalRepository, savedStateHandle: SavedStateHandle,):BaseViewModel(){
    private val _listSub = MutableLiveData<ListSubComic?>()
    val listSub: LiveData<ListSubComic?> get() = _listSub

    private val _listComments = MutableLiveData<ListComment?>()
    val listComments: LiveData<ListComment?> get() = _listComments

    private val _nav = MutableLiveData<NavController?>()
    val nav: LiveData<NavController?> get() = _nav

    fun fetchData(){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val token = localRepository.getToken()
            val newListSub = token?.let { comicRepository.getListSub(it.token) }
            _listSub.postValue(newListSub)
            val listComments = token?.let { commentRepository.getListComment(it.token) }
            _listComments.postValue(listComments)
            isLoading.postValue(false)
        }
        registerEventParentJobFinish()
    }

    fun pushNav(navController: NavController?){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            _nav.postValue(navController);
            isLoading.postValue(false)
        }
    }

}