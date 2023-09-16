package com.app.comicapp

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale

@Composable
fun MySeries() {
    val  sliderList = listOf(
        "https:picsum.photos/id/237/1000/1000",
        "https:picsum.photos/id/244/1000/1000",
        "https:picsum.photos/id/239/1000/1000",
        "https:picsum.photos/id/243/1000/1000",
        "https:picsum.photos/id/236/1000/1000"
    )
    Column(modifier = Modifier.fillMaxSize()) {
        // Hàng đầu
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My Series",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 5.dp)

            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ChevronRight, contentDescription = null)
            }
        }

        // Hàng thứ 2
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0 until 3) {
                Card(
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.widthIn(max=135.dp).padding(5.dp)
                ) {
                    // Ảnh
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(sliderList[i])
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.loading),
                        error = painterResource(id = R.drawable.error),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    // Text
                    Text(
                        text = "World's Strong ajhshdahdja $i",
                        style = MaterialTheme.typography.labelMedium,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.background(Color.Black).padding(top = 5.dp),
                        maxLines = 1
                    )
                }
            }
        }
    }
}