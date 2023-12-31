package com.app.comicapp.ui.more

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.TokenViewModel
import com.app.comicapp.components.GenerComic
import com.app.comicapp.ui.home.HomeViewModal

@Composable
fun MoreScreen(moreViewModel: MoreViewModel = hiltViewModel(), tokenViewModel: TokenViewModel = hiltViewModel(), navController: NavController){
    val token = tokenViewModel.token.observeAsState()
    val user = moreViewModel.user.observeAsState()
    LaunchedEffect(token.value){
        tokenViewModel.getToken()
        Log.e("token", token.value.toString())
        if(token.value!=null){
            moreViewModel.fetchData(token.value!!.token)
        }
    }

    if(user.value!=null){
        Box (Modifier.fillMaxSize()){
            Column( modifier = Modifier ){
                Row {
                    Text(text = "More", modifier = Modifier
                        .width(370.dp)
                        .height(60.dp)
                        .padding(10.dp), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)

                    IconButton(onClick = { navController.navigate("settings") } ) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings")
                    }
                }

                 Column(modifier = Modifier
                     .height(500.dp)
                     .fillMaxWidth()) {

                     AsyncImage(
                         model = ImageRequest.Builder(LocalContext.current)
                             .data(user.value!!.avatar)
                             .crossfade(true)
                             .scale(Scale.FILL)
                             .build(),
                         contentDescription = null,
                         placeholder = painterResource(id = R.drawable.loading),
                         error = painterResource(id = R.drawable.error),
                         modifier = Modifier
                             .fillMaxHeight()
                             .weight(1f),
                         contentScale = ContentScale.Crop
                     )
                     Spacer(modifier = Modifier
                         .fillMaxWidth()
                         .height(80.dp))
                     Row {
                        Text(text = "${user.value!!.name}",modifier = Modifier.fillMaxWidth(),style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.secondary,textAlign = TextAlign.Center)
                     }
                     Spacer(modifier = Modifier
                         .fillMaxWidth()
                         .height(30.dp))
                     Row {
                         Text(text = "Coin : ${user.value!!.coin}",modifier = Modifier.fillMaxWidth(),style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.secondary,textAlign = TextAlign.Center)
                     }
                     Spacer(modifier = Modifier
                         .fillMaxWidth()
                         .height(30.dp))
                     Row {
                         Text(text = "Email: ${user.value!!.email}",modifier = Modifier.fillMaxWidth(),style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.secondary,textAlign = TextAlign.Center)
                     }
                     Spacer(modifier = Modifier
                         .fillMaxWidth()
                         .height(30.dp))
                }
                Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                    Button(onClick = {
                        tokenViewModel.deleteToken()
                        navController.navigate("login")
                    }) {
                        Text(text = "Đăng Xuất")
                    }
                }
            }
        }
    }
}