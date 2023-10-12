package com.app.comicapp.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CarouselCard(navController: NavController) {
    val  pageState = rememberPagerState (initialPage = 1 )
    val  sliderList = listOf(
        "https://st.nettruyenus.com/data/comics/188/dai-quan-gia-la-ma-hoang-904.jpg",
        "https://st.nettruyenus.com/data/comics/32/vo-luyen-dinh-phong.jpg",
        "https://st.nettruyenus.com/data/comics/60/nguoi-xau.jpg",
        "https://st.nettruyenus.com/data/comics/64/than-sung-tien-hoa-8970.jpg",
        "https://st.nettruyenus.com/data/comics/234/dai-tuong-vo-hinh-4411.jpg"
    )
    val currentPage = pageState.currentPage

    val currentPageKey = currentPage.hashCode()

    LaunchedEffect(key1 = currentPageKey, block = {
        // Chuyển sang trang tiếp theo sau 2s
        delay(5000)
        if (currentPage == pageState.pageCount - 1) {
            // Chuyển sang trang đầu tiên
            pageState.animateScrollToPage(0)
        } else {
            // Chuyển sang trang tiếp theo
            pageState.animateScrollToPage(currentPage + 1)
        }

    })

    Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Top) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "New Here?",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(start = 5.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Row {
            HorizontalPager(
                count = sliderList.size,
                state = pageState,
                contentPadding = PaddingValues(horizontal = 65.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 290.dp),
                itemSpacing = 0.dp
            ) {
                    page->
                val mContext = LocalContext.current
                Card (
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = 0.9f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                            alpha = lerp(
                                start = 0.85f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                    onClick = { navController.navigate("comic/6526cef19d151956934b8257") }
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(sliderList[page])
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        placeholder = painterResource(id = R.drawable.loading),
                        error = painterResource(id = R.drawable.error),
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clipToBounds(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }

    }
}