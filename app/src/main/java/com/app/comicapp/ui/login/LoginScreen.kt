package com.app.comicapp.ui.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.app.comicapp.R
import com.app.comicapp.TokenViewModel
import com.app.comicapp.components.ButtonComponent
import com.app.comicapp.components.ClickableLoginTextComponent
import com.app.comicapp.components.DividerTextComponent
import com.app.comicapp.components.HeadingTextComponent
import com.app.comicapp.components.NormalTextComponent
import com.app.comicapp.components.PasswordTextFieldComponent
import com.app.comicapp.components.UnderLinedTextComponent
import com.app.comicapp.components.MyTextFieldComponent
import com.app.comicapp.components.mToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel(), tokenViewModel: TokenViewModel= hiltViewModel(), navController: NavController) {
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

                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    onTextChanged = {
                        loginViewModel.viewModelScope.launch {
                            loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                        }
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.viewModelScope.launch {
                            loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                        }

                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password), onClick = {
                    navController.navigate("forgotPass")
                })

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.viewModelScope.launch {
                            try {
                                loginViewModel.login()
                                loginViewModel.loginInProgress.value = false
                                mToast(mContext,"Đăng nhập thành công")
                                delay(1000)
                                tokenViewModel.setToken(loginViewModel.token.value)
                                navController.navigate("home")
                            }
                            catch (e:Exception){
                                loginViewModel.loginInProgress.value = false
                                mToast(mContext,"Sai email hoặc mật khẩu")
                                Log.e("login", e.message.toString())
                            }
                        }

                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    navController.navigate("signup")
                })
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }


}


