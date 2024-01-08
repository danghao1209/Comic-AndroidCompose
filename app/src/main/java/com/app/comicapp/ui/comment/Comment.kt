package com.app.comicapp.ui.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.app.comicapp.R
import com.app.comicapp.data.entities.Comment
import com.app.comicapp.ui.comic.ComicViewModel

@Composable
fun CommentItem(comment: Comment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(comment.avatar)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            placeholder = painterResource(id = R.drawable.loading),
            error = painterResource(id = R.drawable.error),
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = comment.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = comment.content
            )
        }
    }
    Divider(
        modifier = Modifier.padding(horizontal = 8.dp),
        color = Color.LightGray
    )
}

@Composable
fun CommentList(comicViewModel: ComicViewModel) {
    val comments = comicViewModel.listComments.observeAsState()
    LazyColumn {
        comments.value?.let {
            items(it.comments) { comment ->
                CommentItem(comment = comment)
            }
        }
    }
}

@Composable
fun CommentInput( comicViewModel: ComicViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val textInput = comicViewModel.commentText.observeAsState("")
        TextField(
            value = textInput.value ?: "", // "" là giá trị mặc định khi commentText null
            onValueChange = { comicViewModel.onCommentTextChanged(it) },
            modifier = Modifier.width(330.dp).height(50.dp),
            placeholder = { Text(stringResource(R.string.enter_your_comment)) },
            singleLine = true,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = {
            comicViewModel.sendComment()
        }) {
            Icon(
                painter = painterResource(R.drawable.paperplane),
                contentDescription = "Send"
            )
        }
    }
}
@Composable
fun CommentScreen(navController: NavController,comicViewModel: ComicViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CommentInput(comicViewModel)
        CommentList(comicViewModel)
    }
}