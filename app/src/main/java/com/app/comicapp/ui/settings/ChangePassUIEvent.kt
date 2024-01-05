package com.app.comicapp.ui.settings

sealed class ChangePassUIEvent{

    data class OldPassChange(val oldPass:String): ChangePassUIEvent()
    data class NewPassChange(val newPass: String) : ChangePassUIEvent()
    data class ConfirmNewPassChange(val confirmNewPass: String) : ChangePassUIEvent()
    object ChangePassButtonClicked : ChangePassUIEvent()
}
