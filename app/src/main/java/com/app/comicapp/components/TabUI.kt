package com.app.comicapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.app.comicapp.ui.original.GenerScreen
import com.app.comicapp.ui.profile.SubcribedScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

typealias ComposableFun = @Composable ()->Unit
sealed class TabItem1(val title:String) {

    object Preview : TabItem1(title = "Preview")


    object Episodes: TabItem1(title = "Episodes")

}

sealed class TabItem(val title:String, val screens: ComposableFun) {

    object Preview : TabItem(title = "Preview", screens = {})


    object Episodes: TabItem(title = "Episodes", screens ={})

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem1>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        }) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {Text(tabItem.title) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                enabled = true,
            )

        }


    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs1(tabs: List<TabItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
        indicator = { tabPositions ->
            Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
        }) {
        tabs.forEachIndexed { index, tabItem ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {Text(tabItem.title) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                enabled = true,
            )

        }

    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent(tabs:List<TabItem1>,pagerState: PagerState, navController: NavController) {
    HorizontalPager(count = tabs.size,state=pagerState) { page ->
        if(page ==0){
            PreviewComic(navController = navController)
        }

        if(page ==1){
            Episode(navController = navController)
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent1(tabs:List<TabItem>,pagerState: PagerState) {
    HorizontalPager(count = tabs.size,state=pagerState) { page ->
        tabs[page].screens()
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabComic(navController: NavController) {


    val list = listOf(TabItem1.Preview,TabItem1.Episodes)
    val pagerState = rememberPagerState(initialPage = 0)


    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(tabs = list, pagerState = pagerState)
        TabContent(tabs = list, pagerState = pagerState,navController)
    }

}

sealed class TabProfileItem(val title:String, val screens:ComposableFun) {

    object Subcribed : TabItem(title = "Subcribed", screens = { SubcribedScreen()})
    object Downloads: TabItem(title = "Downloads", screens ={ SubcribedScreen()})

    object Comments : TabItem(title = "Comments", screens = { SubcribedScreen()})

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabProfile() {


    val list = listOf(TabProfileItem.Subcribed,TabProfileItem.Downloads,TabProfileItem.Comments)
    val pagerState = rememberPagerState(initialPage = 0)


    Column(modifier = Modifier.fillMaxSize()) {
        Tabs1(tabs = list, pagerState = pagerState)
        TabContent1(tabs = list, pagerState = pagerState)
    }

}



@OptIn(ExperimentalPagerApi::class)
@Composable
fun GenerComic(navController: NavController) {


    val list = listOf(TabGenerItem.All,TabGenerItem.Action,TabGenerItem.Comedy,TabGenerItem.Drama,TabGenerItem.Romance,TabGenerItem.Horror,TabGenerItem.Fantasy,TabGenerItem.ShortStory,TabGenerItem.Superhero,TabGenerItem.Zombie,TabGenerItem.Supernatural,TabGenerItem.Animals,TabGenerItem.Crime_Mystery,TabGenerItem.Sci_fi,TabGenerItem.Historical,TabGenerItem.Informative,TabGenerItem.Sports,TabGenerItem.All_Ages)

    val pagerState = rememberPagerState(initialPage = 0)


    Column(modifier = Modifier.fillMaxSize()) {
        Tabs2(tabs = list, pagerState = pagerState)
        TabContent2(tabs = list, pagerState = pagerState, navController)
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs2(tabs: List<TabGenerItem>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    Row {
        ScrollableTabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colorScheme.background,
            indicator = { tabPositions ->
                Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions)
            }) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {Text(tabItem.title) },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    enabled = true,
                )

            }


        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabContent2(tabs:List<TabGenerItem>,pagerState: PagerState,navController:NavController) {
    HorizontalPager(count = tabs.size,state=pagerState) { page ->
        GenerScreen(genre = tabs[page].path, navController)
    }
}

sealed class TabGenerItem(val title:String, val path:String) {


    object All : TabGenerItem(title = "All Comic", path = "")
    object Action : TabGenerItem(title = "Action", path = "Action")
    object Comedy : TabGenerItem(title = "Comedy", path = "Comedy")
    object Drama : TabGenerItem(title = "Drama", path = "Drama")
    object Romance : TabGenerItem(title = "Romance", path = "Romance")
    object Horror : TabGenerItem(title = "Horror", path = "Horror")
    object Fantasy : TabGenerItem(title = "Fantasy", path = "Fantasy")
    object ShortStory : TabGenerItem(title = "ShortStory", path = "ShortStory")
    object Superhero: TabGenerItem(title = "Superhero", path = "Superhero")
    object Zombie : TabGenerItem(title = "Zombie", path = "Zombie")
    object Supernatural : TabGenerItem(title = "Supernatural", path = "Supernatural")
    object Animals : TabGenerItem(title = "Animals", path = "Animals")
    object Crime_Mystery : TabGenerItem(title = "Crime_Mystery", path = "Crime_Mystery")
    object Sci_fi : TabGenerItem(title = "Sci_fi", path = "Sci_fi")
    object Historical : TabGenerItem(title = "Historical", path = "Historical")
    object Informative: TabGenerItem(title = "Informative", path = "Informative")
    object Sports : TabGenerItem(title = "Sports", path = "Sports")
    object All_Ages : TabGenerItem(title = "All_Ages", path = "All_Ages")

}