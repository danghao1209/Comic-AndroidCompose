package com.app.comicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.data.entities.Chapter
import com.app.comicapp.ui.comic.ComicViewModel

@Composable
fun Episode(comicViewModel: ComicViewModel = hiltViewModel(),navController: NavController){
    val listChapter = comicViewModel.listChapter.observeAsState()

    if(listChapter.value != null){
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(listChapter.value!!) { chapter ->
                EpisodesItem(chapter,navController)
            }
        }
    }
    else{
        Image(painter = painterResource(id = R.drawable.loading), contentDescription = null, modifier = Modifier.fillMaxWidth())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EpisodesItem(chapter:Chapter,navController: NavController, comicViewModel: ComicViewModel = hiltViewModel()){

    Card (
        shape = RectangleShape,
        modifier = Modifier.padding(bottom = 2.dp ),
        onClick = {
            comicViewModel.setChapter(chapter.id)
            navController.navigate("chapter/${chapter.id}")
        }
    ){
        Row(modifier = Modifier.height(100.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(chapter.thumbChapter)
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading),
                error = painterResource(id = R.drawable.error),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.5f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Chapter ${chapter.titleChapter}",
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.labelLarge,
                )
                Row() {
                    Row {
                        Icon( Icons.Outlined.FavoriteBorder , contentDescription = null )
                        Text(
                            text = "59000",
                            modifier = Modifier.padding(5.dp),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Text(
                        text = "Sep 29, 2023",
                        modifier = Modifier.padding(5.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
            Text(
                text = "#${chapter.chapterNumber}",
                modifier = Modifier.weight(0.5f),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

        }
        Divider(
            color = Color.Gray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}