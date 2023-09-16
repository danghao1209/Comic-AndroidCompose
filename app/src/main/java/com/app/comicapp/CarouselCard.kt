package com.app.comicapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CarouselCard() {
    val  pageState = rememberPagerState (initialPage = 1 )
    val  sliderList = listOf(
        "https:picsum.photos/id/237/1000/1000",
        "https:picsum.photos/id/244/1000/1000",
        "https:picsum.photos/id/239/1000/1000",
        "https:picsum.photos/id/243/1000/1000",
        "https:picsum.photos/id/236/1000/1000"
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
        HorizontalPager(
            count = sliderList.size,
            state = pageState,
            contentPadding = PaddingValues(horizontal = 65.dp),
            modifier = Modifier.fillMaxWidth().heightIn(max = 290.dp),
            itemSpacing = 0.dp
        ) {
            page->
            val mContext = LocalContext.current
            Card (
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                    lerp(
                        start = 0.9f,
                        stop = 1f,
                        fraction = 1f- pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f- pageOffset.coerceIn(0f, 1f)
                    )},
                onClick = {mToast(mContext, page.toString())}
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                    .data(sliderList[page])
                    .crossfade(true)
                    .scale(Scale.FILL)
                    .build(),
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.loading),
                    error = painterResource(id = R.drawable.error)
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        MySeries()
    }
}