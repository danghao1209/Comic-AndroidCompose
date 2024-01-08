package com.app.comicapp.ui.settings

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
class ChangePasswordViewModel @Inject constructor(val localRepository: LocalRepository, val userRepository: UserRepository) : BaseViewModel() {


    var changePassUIState = mutableStateOf(ChangePassUIState())

    var allValidationsPassed = mutableStateOf(false)

    var changeProgress = mutableStateOf(false)


        suspend fun onEvent(event: ChangePassUIEvent) {
        when (event) {
            is ChangePassUIEvent.OldPassChange -> {
                changePassUIState.value = changePassUIState.value.copy(
                    oldPass = event.oldPass
                )
            }

            is ChangePassUIEvent.NewPassChange -> {
                changePassUIState.value = changePassUIState.value.copy(
                    newPass = event.newPass
                )
            }

            is ChangePassUIEvent.ConfirmNewPassChange -> {
                changePassUIState.value = changePassUIState.value.copy(
                    confirmNewPass = event.confirmNewPass
                )
            }

            is ChangePassUIEvent.ChangePassButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }

    private fun validateLoginUIDataWithRules() {


        val oldPassResult = Validator.validatePassword(
            password = changePassUIState.value.oldPass
        )

        val newPassResult = Validator.validatePassword(
            password = changePassUIState.value.newPass
        )
        val confirmNewPassResult = Validator.validatePassword(
            password = changePassUIState.value.confirmNewPass
        )

        changePassUIState.value = changePassUIState.value.copy(
            oldPassError = oldPassResult.status,
            newPassError = newPassResult.status,
            confirmNewPassError = confirmNewPassResult.status
        )

        allValidationsPassed.value = oldPassResult.status && newPassResult.status && confirmNewPassResult.status

    }

    suspend fun login() {
        changeProgress.value = true
        val oldPass = changePassUIState.value.oldPass
        val newPass = changePassUIState.value.newPass
        val confirmNewPass = changePassUIState.value.confirmNewPass
        try {
            val tokens = localRepository.getToken()
            val user = tokens?.let { userRepository.changePass(oldPass,newPass,confirmNewPass, it.token) }

        }catch (e:Error){
            Log.e("LoginFail", e.message.toString())
            changeProgress.value = false
        }


    }

}