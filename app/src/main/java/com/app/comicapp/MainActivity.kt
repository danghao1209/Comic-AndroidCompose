package com.app.comicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.app.comicapp.navigation.NavigationBarWithScaffold
import com.app.comicapp.components.mToast
import com.app.comicapp.ui.chapter.ChapterScreen
import com.app.comicapp.ui.comic.ComicScreen
import com.app.comicapp.ui.forgotpass.ForgotPasswordScreen
import com.app.comicapp.ui.forgotpass.OtpTextFieldScreen
import com.app.comicapp.ui.home.HomeScreen
import com.app.comicapp.ui.login.LoginScreen
import com.app.comicapp.ui.more.MoreScreen
import com.app.comicapp.ui.original.OriginalsScreen
import com.app.comicapp.ui.profile.ProfileScreen
import com.app.comicapp.ui.search.SearchScreen
import com.app.comicapp.ui.settings.ChangePassScreen
import com.app.comicapp.ui.settings.SettingsScreen
import com.app.comicapp.ui.signup.SignUpScreen
import com.app.comicapp.ui.theme.ComicAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val tokenModel by viewModels<TokenViewModel>()
        super.onCreate(savedInstanceState)
        tokenModel.getToken()
        setContent {
            ComicAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable(route = "login") {
                            LoginScreen(navController = navController)
                        }
                        composable(route = "signup") { SignUpScreen(navController = navController) }
                        composable(route = "forgotPass") { ForgotPasswordScreen(navController = navController) }
                        composable(route = "otp") { OtpTextFieldScreen(navController = navController) }
                        composable(route = "search") { SearchScreen(navController = navController) }
                        composable(route = "settings") { SettingsScreen(navController = navController) }
                        composable(route = "changePass") { ChangePassScreen(navController = navController) }
                        composable(route = "home") {
                            NavigationBarWithScaffold(navController = navController) {
                                HomeScreen(
                                    navController = navController
                                )
                            }
                        }
                        composable(route = "profile") {
                            NavigationBarWithScaffold(navController = navController) {
                                ProfileScreen(
                                    navController = navController
                                )
                            }
                        }
                        composable(
                            route = "chapter/{chapterId}",
                            arguments = listOf(navArgument("chapterId") {
                                type = NavType.StringType
                                nullable = false
                            })
                        ) { navBackStackEntry ->
                            //ChapterNavigation(navController= navController, screen = {  })
                            ChapterScreen(
                                navController = navController,
                                chapterId = navBackStackEntry.arguments?.getString("chapterId")
                            )

                        }
                        composable(
                            route = "comic/{comicId}",
                            arguments = listOf(navArgument("comicId") {
                                type = NavType.StringType
                                nullable = false
                            })
                        ) { navBackStackEntry ->
                            ComicScreen(
                                navController = navController,
                                comicId = navBackStackEntry.arguments?.getString("comicId")
                            )
                        }
                        composable(route = "originals") {
                            NavigationBarWithScaffold(navController = navController) {
                                OriginalsScreen(
                                    navController = navController
                                )
                            }
                        }
                        composable(route = "more") {
                            NavigationBarWithScaffold(navController = navController) {
                                MoreScreen(
                                    navController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    inline fun <reified T> NavBackStackEntry.sharedViewModel(navController: NavController): T {
        val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
        val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
        return hiltViewModel(parentEntry)
    }


    @Composable
    fun imageFromURL() {
        Image(
            // on below line we are adding the image url
            // from which we will  be loading our image.
            painter = rememberAsyncImagePainter("https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"),

            // on below line we are adding content
            // description for our image.
            contentDescription = "gfg image",

            // on below line we are adding modifier for our
            // image as wrap content for height and width.
            modifier = Modifier
                .wrapContentSize()
                .wrapContentHeight()
                .wrapContentWidth()
        )

    }


    @Composable
    fun DemoTextField() {
        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text,
            modifier = Modifier.size(width = 100.dp, height = 50.dp),
            onValueChange = { text = it },
            shape = RoundedCornerShape(50.dp),
            isError = false,
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }

    @Composable
    fun SomeContent() {
        // Fetching the local context for using the Toast
        val mContext = LocalContext.current
        // Creating a Box layout to display a Button
        Box(Modifier.fillMaxSize(), Alignment.Center) {
            // Creating a Button and calling the
            // Toast function when clicked
            Button(
                onClick = { mToast(mContext, "hihi") },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onError)
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Like")
            }
        }
    }
}


