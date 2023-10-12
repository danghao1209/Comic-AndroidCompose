package com.app.comicapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserToken(
    @PrimaryKey val id: String,
    val token: String,
)


