package com.app.comicapp.ui.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavController,
    state: SearchState = rememberSearchState()
) {


    Column(
        modifier = modifier.fillMaxSize()
    ) {

        SearchBar(
            query = state.query,
            onQueryChange = { state.query = it },
            onSearchFocusChange = { state.focused = it },
            onClearQuery = { state.query = TextFieldValue("") },
            onBack = {
                state.query = TextFieldValue("")
                navController.navigate("home") },
            searching = state.searching,
            focused = state.focused,
            modifier = modifier
        )

        LaunchedEffect(state.query.text) {
           try {
               state.searching = true
               delay(2000)
               if(state.query.text!=null || state.query.text!= ""){
                   searchViewModel.searchData(state.query.text)
               }
               state.searchResults = searchViewModel.comicList.value
               state.searching = false
           }catch (e :Exception){
               Log.e("Search",e.message.toString() )
           }
        }

        when (state.searchDisplay) {
            SearchDisplay.InitialResults -> {

            }
            SearchDisplay.NoResults -> {

            }
            SearchDisplay.Results -> {

            }

            else -> {}
        }
        val comicList = searchViewModel.comicList.observeAsState()
        if(comicList.value !=null){
            LazyColumn(){
                items(comicList.value!!.size){ comic->
                    Card(
                        shape = MaterialTheme.shapes.small,
                        modifier =
                        Modifier
                            .fillMaxWidth()
                            .heightIn(max = 100.dp)
                            .padding(5.dp),
                        onClick = {navController.navigate("comic/${comicList.value!![comic].id}")}
                    ) {
                       Row {
                           AsyncImage(
                               model = ImageRequest.Builder(LocalContext.current)
                                   .data(comicList.value!![comic].thumbImg)
                                   .crossfade(true)
                                   .scale(Scale.FILL)
                                   .build(),
                               contentDescription = null,
                               placeholder = painterResource(id = R.drawable.loading),
                               error = painterResource(id = R.drawable.error),
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .heightIn(0.dp, 100.dp)
                                   .weight(1f),
                               contentScale = ContentScale.Crop
                           )
                           Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(3f)) {

                               Text(
                                   text = comicList.value!![comic].title,
                                   style = MaterialTheme.typography.titleMedium,
                                   overflow = TextOverflow.Ellipsis,
                                   modifier = Modifier.background(Color.Black).padding(3.dp).heightIn(max=100.dp).fillMaxSize().weight(3f).padding(5.dp),
                                   maxLines = 1,
                                   color = MaterialTheme.colorScheme.primary
                               )
                               Text(
                                   text = comicList.value!![comic].genre,
                                   style = MaterialTheme.typography.titleSmall,
                                   overflow = TextOverflow.Ellipsis,
                                   modifier = Modifier.background(Color.Black).padding(3.dp).heightIn(max=100.dp).fillMaxSize().weight(3f).weight(2f),
                                   maxLines = 1,
                                   color = MaterialTheme.colorScheme.secondary,
                                   textAlign = TextAlign.Center,

                               )

                           }
                       }
                    }
                }
            }
        }
    }
}