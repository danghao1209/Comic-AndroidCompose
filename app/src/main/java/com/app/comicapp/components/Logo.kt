package com.app.comicapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.comicapp.R

@Composable
fun Logo(navController: NavController){
    Row (
        modifier = Modifier.fillMaxWidth().heightIn(max=100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logocomic),
            contentDescription ="logo",
            modifier = Modifier.widthIn(max=120.dp).heightIn(max=120.dp).scale(1f).fillMaxWidth().align(Alignment.CenterVertically), contentScale = ContentScale.Crop)


        IconButton(
            modifier = Modifier.size(50.dp).padding(10.dp).fillMaxWidth().align(Alignment.CenterVertically),
            onClick = {
                // Thực hiện hành động nào đó
                navController.navigate("search")
            }
        ) {
            Icon(imageVector = Icons.Filled.Search, modifier = Modifier.size(50.dp), contentDescription = null)
        }
    }
}


