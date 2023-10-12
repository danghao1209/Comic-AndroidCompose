package com.app.comicapp.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.data.repositories.UserRepository
import com.app.comicapp.ui.comic.ComicViewModel

@Composable
fun HeaderComic(comic :ComicAll, navController:NavController, comicViewModel:ComicViewModel ){
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        item { HeaderButton(navController, comicViewModel) }
        item { Content(comic) }
    }
}

@Composable
fun HeaderButton(navController:NavController, comicViewModel:ComicViewModel,){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        IconButton(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            onClick = {
                navController.popBackStack()
            }
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack, modifier = Modifier.size(50.dp), contentDescription = null)
        }

        Row {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "+Subscribe")
            }
            
            
            IconButton(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                onClick = {
                    try {
                        comicViewModel.subcribe()
                    }catch (e:Exception){
                        Log.e("Lỗi sub","sub")
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Info, modifier = Modifier.size(50.dp), contentDescription = null)
            }
            
        }
    }
}

@Composable
fun ViewRate(view:Number, follow:Number, rate:Number){
    Row (modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 50.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.RemoveRedEye,
                contentDescription = "View",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "$view",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircleOutline,
                contentDescription = "View",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "$follow",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircleOutline,
                contentDescription = "View",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = "$follow",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Rate")
            }
        }
    }
}


@Composable
fun Content(comic :ComicAll){
    Row (modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 400.dp)
        .padding(20.dp)){
        Column(modifier = Modifier
            .weight(2f)
            .fillMaxWidth()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 50.dp)
                .padding(5.dp)
            ) {
                Text(text = comic.genre, style = MaterialTheme.typography.labelMedium, maxLines = 1)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 50.dp)
                .padding(5.dp)
            ) {
                Text(text = comic.title, style = MaterialTheme.typography.titleLarge, maxLines = 1)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 50.dp)
                .padding(5.dp)
            ) {
                Text(text = "Đăng Hào", style = MaterialTheme.typography.labelMedium, maxLines = 1)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 50.dp)
                .padding(5.dp)
            ) {
                Text(text = comic.description, style = MaterialTheme.typography.labelMedium, maxLines = 1,overflow = TextOverflow.Ellipsis,)
            }
        }

        Column(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            ) {
           Surface(
               modifier = Modifier.border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary), shape = CircleShape).clip(shape = CircleShape),
               ) {
               AsyncImage(
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(comic.thumbImg)
                       .crossfade(true)
                       .scale(Scale.FILL)
                       .build(),
                   contentDescription = null,
                   placeholder = painterResource(id = R.drawable.loading),
                   error = painterResource(id = R.drawable.error),
                   modifier = Modifier.fillMaxWidth().heightIn(max=150.dp),
                   contentScale = ContentScale.Crop,
               )
           }
        }
    }
}