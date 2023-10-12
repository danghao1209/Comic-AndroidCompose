package com.app.comicapp


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.database.entities.UserToken
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(private val localRepository: LocalRepository): BaseViewModel(){


    private val _token = MutableLiveData<UserToken?>()
    val token: LiveData<UserToken?> get() = _token


    private val _rederect = MutableLiveData<Boolean>(false)
    val rederect: LiveData<Boolean> get() = _rederect
    init {
        if ( localRepository ==null) {
            throw IllegalStateException("Required value was null.")
        }
    }
    fun getToken() {
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            val tokenGet = localRepository.getToken()
            if(tokenGet != null){
                val newUserToken = UserToken(tokenGet.id, tokenGet.token)
                _token.postValue(newUserToken)
                Log.e("TokenGet", token.value.toString())
            }else{
                _rederect.postValue(true)
            }
            isLoading.postValue(false)
        }

        registerEventParentJobFinish()
    }

    fun setToken(userToken: UserToken?){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            _token.postValue(userToken)
            if (userToken != null) {
                localRepository.saveToken(userToken)
            }else{
                _rederect.postValue(true)
            }
            isLoading.postValue(false)
        }
        registerEventParentJobFinish()
    }

    fun deleteToken(){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            localRepository.deleteToken()
            _token.postValue(null)
            isLoading.postValue(false)
        }
        registerEventParentJobFinish()
    }

    fun setRederect(){
        parentJob = viewModelScope.launch(exceptionHandler){
            isLoading.postValue(true)
            if(rederect.value == true){
                _rederect.postValue(false)
            }
            isLoading.postValue(false)
        }
        registerEventParentJobFinish()
    }







}