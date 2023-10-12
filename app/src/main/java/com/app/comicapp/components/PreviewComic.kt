package com.app.comicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.ui.comic.ComicViewModel

@Composable
fun PreviewComic(comicViewModel: ComicViewModel = hiltViewModel(), navController:NavController){
    val comic = comicViewModel.comic.observeAsState()
    if(comic.value != null){
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(comic.value!!.previewImg) { previewImg ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(previewImg)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.loading),
                    error = painterResource(id = R.drawable.error),
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
    else{
        Image(painter = painterResource(id = R.drawable.loading), contentDescription = null, modifier = Modifier.fillMaxWidth())
    }

}


