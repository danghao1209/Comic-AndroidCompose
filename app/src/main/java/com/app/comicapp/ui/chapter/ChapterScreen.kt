package com.app.comicapp.ui.chapter

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R



@Composable
fun ChapterScreen(chapterViewModel: ChapterViewModel = hiltViewModel(), navController: NavController, chapterId: String?) {


    LaunchedEffect(Unit) {
        chapterViewModel.fetchData(chapterId)
    }
    val data = chapterViewModel.chapter.observeAsState()


    Surface(modifier = Modifier.fillMaxSize()) {
        Box {
            Box(Modifier.fillMaxWidth()) {
                if (data.value != null) {
                    LazyColumn() {
                        items(data.value!!.image) { item ->
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(item)
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
                }else{
                    Image(painter = painterResource(id = R.drawable.loading), contentDescription = "loading", modifier = Modifier.fillMaxWidth() )
                }
            }
        }
    }
}




