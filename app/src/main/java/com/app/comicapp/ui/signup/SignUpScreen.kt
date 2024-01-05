package com.app.comicapp.ui.signup

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.comicapp.components.ButtonComponent
import com.app.comicapp.components.CheckboxComponent
import com.app.comicapp.components.DividerTextComponent
import com.app.comicapp.components.HeadingTextComponent
import com.app.comicapp.components.MyTextFieldComponent
import com.app.comicapp.components.NormalTextComponent
import com.app.comicapp.R
import com.app.comicapp.components.ClickableLoginTextComponent
import com.app.comicapp.components.PasswordTextFieldComponent
import com.app.comicapp.components.mToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(signupViewModel: SignupViewModel = hiltViewModel(),navController: NavController) {
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
            Column(modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)) {

                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(
                    labelValue = "Full Name",
                    painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.firstNameError
                )

                MyTextFieldComponent(
                    labelValue = "Username",
                    painterResource = painterResource(id = R.drawable.profile),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.lastNameError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.message),
                    onTextChanged = {
                        signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.passwordError
                )

                PasswordTextFieldComponent(
                    labelValue = "RePassword",
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signupViewModel.onEvent(SignupUIEvent.RePasswordChanged(it))
                    },
                    errorStatus = signupViewModel.registrationUIState.value.rePasswordError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {

                        signupViewModel.viewModelScope.launch {
                            try {
                                signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                                mToast(mContext,"Đăng ký thành công")
                                delay(1000)
                                navController.navigate("login")
                            }
                            catch (e:Exception){
                                mToast(mContext,"Đăng ký Thất bại")
                            }
                        }
                    },
                    isEnabled = signupViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                    navController.navigate("login")
                })
            }

        }

        if(signupViewModel.signUpInProgress.value) {
            CircularProgressIndicator()
        }
    }


}




