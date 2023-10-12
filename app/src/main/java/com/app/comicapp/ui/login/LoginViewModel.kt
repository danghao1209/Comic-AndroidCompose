package com.app.comicapp.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import com.app.comicapp.base.BaseViewModel
import com.app.comicapp.data.database.entities.UserToken
import com.app.comicapp.data.repositories.LocalRepository
import com.app.comicapp.data.repositories.UserRepository
import com.app.comicapp.validator.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val localRepository: LocalRepository, val userRepository: UserRepository) : BaseViewModel() {


    var loginUIState = mutableStateOf(LoginUIState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    var token = MutableLiveData<UserToken>()
        private set get



        suspend fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )

        allValidationsPassed.value = emailResult.status && passwordResult.status

    }

    suspend fun login() {

        loginInProgress.value = true
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        Log.e("email", email)
        try {
            val user = userRepository.login(email,password)
            val id = user?.id
            val tokenAcess = user?.accessToken
            val userToken = UserToken(id.toString(),tokenAcess.toString())
            token.value = userToken

        }catch (e:Error){
            Log.e("LoginFail", e.message.toString())
            loginInProgress.value = false
        }


    }

}