package com.app.comicapp.ui.comic

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.app.comicapp.components.HeaderComic
import com.app.comicapp.components.TabComic

@Composable
fun ComicScreen(comicViewModel: ComicViewModel = hiltViewModel(),navController: NavController, comicId:String?){
    LaunchedEffect(Unit) {
        comicViewModel.fetchData(comicId)
        comicViewModel.fetchListChapter(comicId)
    }

    val comic = comicViewModel.comic.observeAsState()
    val listChapter = comicViewModel.listChapter.observeAsState()
    if (comic.value != null && listChapter.value !=null){
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            Column( modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)){
                Row {
                    HeaderComic(comic.value!!,navController, comicViewModel)
                }
                Row {
                    TabComic(navController)
                }
                Row {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }

}
