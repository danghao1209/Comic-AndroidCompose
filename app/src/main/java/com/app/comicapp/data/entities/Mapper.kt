package com.app.comicapp.data.entities

import com.app.comicapp.data.database.entities.UserToken


fun User.userToken(): UserToken {
    return UserToken(
        id=this.id,
        token= this.accessToken,
    )
}