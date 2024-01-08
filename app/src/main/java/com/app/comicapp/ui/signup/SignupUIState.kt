package com.app.comicapp.ui.signup

data class SignupUIState(
    var firstName :String = "",
    var username  :String = "",
    var email  :String = "",
    var password  :String = "",
    var rePass  :String = "",


    var firstNameError :Boolean = false,
    var lastNameError : Boolean = false,
    var emailError :Boolean = false,
    var passwordError : Boolean = false,
    var rePasswordError : Boolean = false,

)
