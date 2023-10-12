package com.app.comicapp.data.repositories

import android.provider.ContactsContract.CommonDataKinds.Email
import com.app.comicapp.data.database.entities.UserToken
import com.app.comicapp.data.entities.User
import com.app.comicapp.data.services.LocalService
import com.app.comicapp.data.services.UserService
import com.app.comicapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService,
    private val localService: LocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

     suspend fun login(username: String, password:String): User? = withContext(dispatcher) {
        userService.login(username,password)
    }

    suspend fun logout(): UserToken? = withContext(dispatcher) {
        localService.getToken()
    }

    suspend fun singup(username:String, password: String, name:String, email: String): User? = withContext(dispatcher) {
        userService.register(username,password,name,email)
    }

    suspend fun subcribe(comicId:String): User? = withContext(dispatcher) {
        userService.subcribe(comicId)
    }

    suspend fun getInfo(token:String): User? = withContext(dispatcher) {
        userService.getInfo(token)
    }
}