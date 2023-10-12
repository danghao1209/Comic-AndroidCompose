package com.app.comicapp.ui.more

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.database.entities.UserToken
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.entities.User
import com.app.comicapp.data.repositories.ComicRepository
import com.app.comicapp.data.repositories.LocalRepository
import com.app.comicapp.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(private val userRepository: UserRepository, val localRepository: LocalRepository):
    BaseViewModel(){
    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user


    init {
        if (userRepository == null) {
            throw IllegalStateException("Required value was null.")
        }
        //getToken()
        //fetchData()
    }
    fun fetchData(token: String){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)

            val action = userRepository.getInfo(token )
            _user.postValue(action)


        }

        registerEventParentJobFinish()
    }

}