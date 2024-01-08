package com.app.comicapp.ui.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comicapp.R
import com.app.comicapp.components.ButtonComponent
import com.app.comicapp.components.DividerTextComponent
import com.app.comicapp.components.HeadingTextComponent
import com.app.comicapp.components.PasswordTextFieldComponent
import com.app.comicapp.components.mToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChangePassScreen(changePasswordViewModel: ChangePasswordViewModel = hiltViewModel(), navController: NavController) {
    val mContext = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                HeadingTextComponent(value = "Password")
                Spacer(modifier = Modifier.height(20.dp))

                PasswordTextFieldComponent(
                    labelValue = "Password Old",
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        changePasswordViewModel.viewModelScope.launch {
                            changePasswordViewModel.onEvent(ChangePassUIEvent.OldPassChange(it))
                        }

                    },
                    errorStatus = changePasswordViewModel.changePassUIState.value.oldPassError
                )
                Spacer(modifier = Modifier.height(40.dp))
                PasswordTextFieldComponent(
                    labelValue = "New Password",
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        changePasswordViewModel.viewModelScope.launch {
                            changePasswordViewModel.onEvent(ChangePassUIEvent.NewPassChange(it))
                        }

                    },
                    errorStatus = changePasswordViewModel.changePassUIState.value.newPassError
                )
                Spacer(modifier = Modifier.height(40.dp))
                PasswordTextFieldComponent(
                    labelValue = "Confirm New Password",
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        changePasswordViewModel.viewModelScope.launch {
                            changePasswordViewModel.onEvent(ChangePassUIEvent.ConfirmNewPassChange(it))
                        }

                    },
                    errorStatus = changePasswordViewModel.changePassUIState.value.confirmNewPassError
                )
                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = "Confirm",
                    onButtonClicked = {
                        changePasswordViewModel.viewModelScope.launch {
                            try {
                                changePasswordViewModel.login()
                                changePasswordViewModel.changeProgress.value = false
                                mToast(mContext,"Changed Password Successfully ")
                                delay(1000)
                                navController.navigate("more")
                            }
                            catch (e:Exception){
                                mToast(mContext,"Password change failed")
                                Log.e("login", e.message.toString())
                            }
                        }

                    },
                    isEnabled = changePasswordViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()
            }
        }

        if(changePasswordViewModel.changeProgress.value) {
            CircularProgressIndicator()
        }
    }


}