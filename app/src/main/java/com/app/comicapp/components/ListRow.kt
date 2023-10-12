package com.app.comicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.data.entities.ComicAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListRow(comicList: List<ComicAll>?, title:String, navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 5.dp)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }
        }

        if (comicList != null) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(comicList.size) { comic ->
                    Card(
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier
                            .width(135.dp)
                            .padding(5.dp),
                        onClick = {navController.navigate("comic/${comicList[comic].id}")}
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(comicList[comic].thumbImg)
                                .crossfade(true)
                                .scale(Scale.FILL)
                                .build(),
                            contentDescription = null,
                            placeholder = painterResource(id = R.drawable.loading),
                            error = painterResource(id = R.drawable.error),
                            modifier = Modifier.fillMaxWidth().heightIn(0.dp,135.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = comicList[comic].title,
                            style = MaterialTheme.typography.labelMedium,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.background(Color.Black).padding(top=3.dp, bottom = 3.dp, start = 6.dp, end = 3.dp).fillMaxWidth(),
                            maxLines = 1,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        } else {
            // Hiển thị thông báo cho người dùng
        }
    }
}