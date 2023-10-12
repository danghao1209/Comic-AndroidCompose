package com.app.comicapp.di

import android.content.Context
import androidx.room.Room
import com.app.comicapp.data.database.AppDB
import com.app.comicapp.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context):AppDB{
        return Room.databaseBuilder(appContext, AppDB::class.java, "appdb").build()
    }

    @Provides
    fun provideUserDao(appDB: AppDB):UserDao{
        return appDB.userDao()
    }
}