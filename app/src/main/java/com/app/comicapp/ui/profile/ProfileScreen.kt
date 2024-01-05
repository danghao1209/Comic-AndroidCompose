package com.app.comicapp.ui.profile

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.comicapp.components.TabProfile

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = hiltViewModel(), navController: NavController){
    LaunchedEffect(Unit){
        profileViewModel.fetchData()
        profileViewModel.pushNav(navController);
    }
    Box (Modifier.fillMaxSize()){
        Column( modifier = Modifier ){
            Row {
                Text(text = "My Series", modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp), style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.primary)
            }
            Row(modifier = Modifier.fillMaxSize()) {
                TabProfile()
            }
        }
    }
}