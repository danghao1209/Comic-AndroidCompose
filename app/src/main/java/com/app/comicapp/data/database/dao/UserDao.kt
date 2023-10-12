package com.app.comicapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.comicapp.data.database.entities.UserToken

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserToken)

    @Query("SELECT * FROM user")
    fun getUser(): UserToken?

    @Query("DELETE FROM user")
    fun delete()
}