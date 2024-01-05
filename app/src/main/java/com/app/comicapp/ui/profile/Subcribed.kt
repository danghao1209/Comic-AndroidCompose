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
import androidx.navigation.NavController
import com.app.comicapp.components.SubcribedItem

@Composable
fun SubcribedScreen(profileViewModel: ProfileViewModel= hiltViewModel()){
    val nav = profileViewModel.nav.observeAsState()
    val subs = profileViewModel.listSub.observeAsState()
    LazyColumn(Modifier.fillMaxSize()){
        subs.value?.listSub?.let {
            items(it.size){ sub ->
                nav.value?.let { it1 -> SubcribedItem(it1, subs.value?.listSub!![sub]) }
            }
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}