package com.app.comicapp.data.repositories

import com.app.comicapp.data.database.entities.UserToken
import com.app.comicapp.data.entities.User
import com.app.comicapp.data.services.LocalService
import com.app.comicapp.data.services.UserService
import com.app.comicapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val localService: LocalService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun saveToken(token: UserToken) = withContext(dispatcher) {
        localService.saveToken(token)
    }

    suspend fun getToken(): UserToken? = withContext(dispatcher) {
        localService.getToken()
    }

    suspend fun deleteToken() = withContext(dispatcher) {
        localService.deleteToken()
    }


}