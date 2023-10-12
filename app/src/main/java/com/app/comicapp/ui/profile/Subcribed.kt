package com.app.comicapp.ui.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.comicapp.components.SubcribedItem

@Composable
fun SubcribedScreen(){
    LazyColumn(Modifier.fillMaxSize()){
        items(10){
            SubcribedItem()
        }

    }
}