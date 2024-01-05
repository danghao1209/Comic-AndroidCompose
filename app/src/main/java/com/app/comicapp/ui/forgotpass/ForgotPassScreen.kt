package com.app.comicapp.ui.forgotpass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.comicapp.R
import com.app.comicapp.components.UnderLinedTextComponent
import com.app.comicapp.ui.comic.ComicViewModel

@Composable
fun ForgotPasswordScreen(forgotPassViewModel: ForgotPassViewModel = hiltViewModel(), navController: NavController,) {
    var email by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "FORGOT YOUR PASSWORD?",
            color = MaterialTheme.colors.secondary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 16.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_forgot_password_illustration),
            contentDescription = "",
            modifier = Modifier.size(240.dp)
        )
        Card(
            backgroundColor = MaterialTheme.colors.onBackground,
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(vertical = 20.dp)
            ) {
                Text(
                    text = "Enter your usename.",
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp
                )
                Text(
                    text = "The OTP will be sent to your email.",
                    color = MaterialTheme.colors.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp
                )
                TextField(
                    value = email, onValueChange = { email = it },
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.primaryVariant,
                        backgroundColor = MaterialTheme.colors.background,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true,
                    leadingIcon = {
                        Row(
                            modifier = Modifier.padding(start = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email_outline),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(6.dp)
                            )
                            Spacer(
                                modifier = Modifier
                                    .width(1.dp)
                                    .height(24.dp)
                                    .background(MaterialTheme.colors.primary)
                            )
                        }
                    },
                    placeholder = {
                        Text(text = "Username", color = Color.Gray)
                    },
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )


                Button(
                    onClick = {
                              navController.navigate("otp")
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.primary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp),
                    contentPadding = PaddingValues(vertical = 14.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 0.dp,
                        pressedElevation = 2.dp
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = "Send OTP",
                        color = MaterialTheme.colors.secondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


            }

        }
        TextButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            UnderLinedTextComponent(value = "Remember password? Login", onClick = {
                navController.navigate("login")
            })
        }
    }
}