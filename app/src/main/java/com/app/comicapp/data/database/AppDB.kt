package com.app.comicapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.comicapp.data.database.dao.UserDao
import com.app.comicapp.data.database.entities.UserToken

@Database(entities = [UserToken::class], version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {
    abstract fun userDao(): UserDao
}