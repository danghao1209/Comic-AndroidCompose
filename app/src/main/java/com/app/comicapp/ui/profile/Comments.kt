package com.app.comicapp.ui.profile

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.comicapp.components.CommentItems
import com.app.comicapp.components.SubcribedItem

@Composable
fun CommentsScreen(profileViewModel: ProfileViewModel= hiltViewModel()){
    val nav = profileViewModel.nav.observeAsState()
    val listComments = profileViewModel.listComments.observeAsState()
    LazyColumn(Modifier.fillMaxSize()){
        listComments.value?.data?.let {
            items(it.size){ comment ->
                nav.value?.let { it1 -> CommentItems(it1, listComments.value?.data!![comment]) }
            }
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}