package com.app.comicapp.ui.original

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerScreen(genre:String, navController: NavController,moreViewModel: MoreViewModel = hiltViewModel()){
    LaunchedEffect(genre){
        try {
            moreViewModel.fetchData(genre)
        }catch (e :Exception){
            Log.e("except", e.message.toString())
        }
    }

    val comic = moreViewModel.comic.observeAsState()

    if(comic.value!= null){
        LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(5.dp)){
            items(comic.value!!.size) { index->
                Card(
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier
                        .width(135.dp)
                        .padding(5.dp),
                    onClick = {navController.navigate("comic/${comic.value!![index].id}")}
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(comic.value!![index].thumbImg)
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.loading),
                        error = painterResource(id = R.drawable.error),
                        modifier = Modifier.fillMaxWidth().heightIn(0.dp,135.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = comic.value!![index].title,
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.background(Color.Black).padding(top=3.dp, bottom = 3.dp, start = 6.dp, end = 3.dp).fillMaxWidth(),
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

