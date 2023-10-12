package com.app.comicapp.data.services

import com.app.comicapp.data.database.dao.UserDao
import com.app.comicapp.data.database.entities.UserToken
import javax.inject.Inject

class LocalService @Inject constructor(private val userDao: UserDao) {


    suspend fun deleteToken() {
        userDao.delete()
    }

    suspend fun getToken(): UserToken? {
        return userDao.getUser()
    }

    suspend fun saveToken(token: UserToken) {
        userDao.insert(token)
    }
}