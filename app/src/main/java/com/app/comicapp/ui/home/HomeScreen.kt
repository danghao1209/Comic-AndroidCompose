package com.app.comicapp.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.comicapp.TokenViewModel
import com.app.comicapp.components.CarouselCard
import com.app.comicapp.components.ListRow
import com.app.comicapp.components.Logo

@Composable
fun HomeScreen(homeViewModal: HomeViewModal= hiltViewModel(), tokenViewModel: TokenViewModel = hiltViewModel(), navController: NavController) {

    LaunchedEffect(Unit){
        homeViewModal.fetchData()
        tokenViewModel.getToken()
    }

    val rederect = tokenViewModel.rederect.observeAsState()

    val treding = homeViewModal.trending.observeAsState()
    val topseries = homeViewModal.topseries.observeAsState()
    val newarrvals = homeViewModal.newarrvals.observeAsState()
    val action = homeViewModal.action.observeAsState()

    if (rederect.value == true){
        navController.navigate("login")
        Log.e("rederect", "rederect.value")
        return
    }

    if(treding != null || topseries != null || newarrvals != null || action != null ){
        Box (Modifier.fillMaxSize()){
            LazyColumn(Modifier.fillMaxSize()){
                item {
                    Logo(navController)
                }
                item { CarouselCard(navController) }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    ListRow(title = "Trending", comicList = treding.value, navController =navController)
                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    ListRow(title = "New Arrvals", comicList = newarrvals.value,navController=navController)

                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    ListRow(title = "Top Series", comicList = topseries.value,navController=navController)

                }

                item {
                    Spacer(modifier = Modifier.height(30.dp))
                    ListRow(title = "Action", comicList = action.value,navController=navController)

                }


                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }

            }
        }
    }
}



