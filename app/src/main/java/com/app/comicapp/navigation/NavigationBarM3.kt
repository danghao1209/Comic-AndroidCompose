package com.app.comicapp.navigation

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBackIos
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.comicapp.components.ComposableFun
import com.app.comicapp.data.entities.ComicAll
import com.app.comicapp.ui.chapter.ChapterViewModel
import com.app.comicapp.ui.comic.ComicViewModel

@Composable
fun NavigationBarWithScaffold(navController: NavController, screen: ComposableFun) {
    Scaffold(
        bottomBar = { NavigationBarM3(navController) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(3.dp)
        ) {
        }
        screen()
    }
}

@Composable
fun NavigationBarM3(navController: NavController) {
    var selectedItem by remember { mutableStateOf(0) }
    val barItems = listOf(
        BarItem(
            title = "FOR YOU",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            route = "home"
        ),
        BarItem(
            title = "ORIGINALS",
            selectedIcon = Icons.Filled.Face,
            unselectedIcon = Icons.Outlined.Face,
            route = "originals"
        ),
        BarItem(
            title = "MY",
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            route = "profile"
        ),
        BarItem(
            title = "MORE",
            selectedIcon = Icons.Filled.MoreVert,
            unselectedIcon = Icons.Outlined.MoreVert,
            route = "more"
        )
    )

    NavigationBar {
        barItems.forEachIndexed { index, barItem ->
            val selected = selectedItem == index
            NavigationBarItem(
                selected = selected,
                onClick = {

                    try {
                        selectedItem = index
                        navController?.navigate(barItem.route)
                    }catch (e:Error){
                        Log.e("loi",e.message.toString())
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) barItem.selectedIcon else barItem.unselectedIcon,
                        contentDescription = barItem.title
                    )
                },
                label = { Text(text = barItem.title) },
                alwaysShowLabel = true
            )
        }
    }
}

data class BarItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)


@Composable
fun ChapterNavigation(navController: NavController, screen: ComposableFun,comicViewModel: ComicViewModel= hiltViewModel(), chapterViewModel: ChapterViewModel= hiltViewModel()) {
    val chapter  = chapterViewModel.chapter.observeAsState()
    /*
    LaunchedEffect(Unit){
        comicViewModel.fetchData(chapter.value!!.comicId)
        comicViewModel.fetchListChapter(chapter.value!!.comicId)
    }

     */

    val comic = comicViewModel.comic.observeAsState()
    val preChapter = comicViewModel.backChapter.observeAsState()
    val nextChapter = comicViewModel.nextChapter.observeAsState()
    Log.e("hah", comic.value.toString())
    Scaffold(

        bottomBar = { ChapterBarM3(navController = navController, comic = comic.value, preChapter =preChapter.value, nextChapter = nextChapter.value) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(3.dp)
        ) {

        }

        screen()
    }
}


@Composable
fun ChapterBarM3(navController: NavController, comic:ComicAll?, nextChapter: String?, preChapter: String?) {
    var selectedItem by remember { mutableStateOf(0) }


    if(comic != null ){
        val barItems = listOf(
            BarItem(
                title = "Quay lại",
                selectedIcon = Icons.Filled.ArrowBack,
                unselectedIcon = Icons.Outlined.ArrowBack,
                route = "comic/${comic.id}"
            ),
            BarItem(
                title = "Pre",
                selectedIcon = Icons.Filled.ArrowBackIos,
                unselectedIcon = Icons.Outlined.ArrowBackIos,
                route = "chapter/${preChapter}"
            ),
            BarItem(
                title = "Next",
                selectedIcon = Icons.Filled.ArrowForwardIos,
                unselectedIcon = Icons.Outlined.ArrowForwardIos,
                route = "chapter/${nextChapter}"
            )
        )

        NavigationBar {
            barItems.forEachIndexed { index, barItem ->
                val selected = selectedItem == index
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        try {
                            navController?.navigate(barItem.route)
                        }catch (e:Error){
                            Log.e("loi",e.message.toString())
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = if (selected) barItem.selectedIcon else barItem.unselectedIcon,
                            contentDescription = barItem.title
                        )
                    },
                    label = { Text(text = barItem.title) },
                    alwaysShowLabel = true
                )
            }
        }
    }
}