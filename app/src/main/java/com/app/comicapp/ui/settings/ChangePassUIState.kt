package com.app.comicapp.ui.settings

data class ChangePassUIState(
    var oldPass  :String = "",
    var newPass  :String = "",
    var confirmNewPass  :String = "",

    var oldPassError :Boolean = false,
    var newPassError : Boolean = false,
    var confirmNewPassError : Boolean = false
)
