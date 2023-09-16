package com.app.comicapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.app.comicapp.ui.theme.ComicAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationBarWithScaffold()
                }
            }
        }
    }
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
fun DemoTextField(){
    var text by rememberSaveable { mutableStateOf("") }

    TextField(value = text,
        modifier = Modifier.size(width = 100.dp, height = 50.dp),
        onValueChange = {text = it},
        shape = RoundedCornerShape(50.dp),
        isError=false,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        )
        )
}

@Composable
fun SomeContent(){
    // Fetching the local context for using the Toast
    val mContext = LocalContext.current
    // Creating a Box layout to display a Button
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        // Creating a Button and calling the
        // Toast function when clicked
        Button(onClick = { mToast(mContext,"hihi") }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onError)) {
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
public fun mToast(context: Context, title: String){
    Toast.makeText(context, "This is a Sample Toast $title", Toast.LENGTH_LONG).show()
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComicAppTheme {
        HomeScreen()
    }
}