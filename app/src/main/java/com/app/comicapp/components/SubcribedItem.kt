package com.app.comicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.data.entities.ComicAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubcribedItem(){
    //comic: ComicAll
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp)
            .clip(MaterialTheme.shapes.small),
        onClick = {}
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://c1.staticflickr.com/9/8112/8477434985_5f637b7d84_z.jpg")
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.loading),
                error = painterResource(id = R.drawable.error),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "hihi",
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .weight(2f)
                    .align(Alignment.CenterVertically),
                maxLines = 1
            )
            IconButton(
                modifier = Modifier.size(50.dp).padding(10.dp).fillMaxWidth().align(Alignment.CenterVertically).weight(1f),
                onClick = {
                    // Thực hiện hành động nào đó
                }
            ) {
                Icon(imageVector = Icons.Filled.CheckCircle, modifier = Modifier.size(50.dp), contentDescription = null)
            }
        }
    }
}