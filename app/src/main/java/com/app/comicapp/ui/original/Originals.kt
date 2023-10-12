package com.app.comicapp.ui.original

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.comicapp.components.GenerComic

@Composable
fun OriginalsScreen(navController: NavController){

    Box (Modifier.fillMaxSize()){
        Column( modifier = Modifier ){
            Row {
                Text(text = "Comics", modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(5.dp), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            }

            Row(modifier = Modifier.fillMaxSize()) {
                GenerComic(navController)
            }
        }
    }
}