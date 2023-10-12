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
import androidx.compose.foundation.lazy.items
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

data class Comic(
    val url: String,
    val id: String
)
@Composable
fun MySeries(listComic:List<Comic>? = null, navController: NavController) {

    val firstFiveComic = listComic?.take(5)

    val comicList = listOf(
        Comic(
            url = "https://st.nettruyenus.com/data/comics/188/dai-quan-gia-la-ma-hoang-904.jpg",
            id = "188"
        ),
        Comic(
            url = "https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg",
            id = "32"
        ),
        Comic(
            url = "https://st.nettruyenus.com/data/comics/60/nguoi-xau.jpg",
            id = "60"
        ),
        Comic(
            url = "https://st.nettruyenus.com/data/comics/64/than-sung-tien-hoa-8970.jpg",
            id = "64"
        ),
        Comic(
            url = "https://st.nettruyenus.com/data/comics/234/dai-tuong-vo-hinh-4411.jpg",
            id = "234"
        )
    )


}