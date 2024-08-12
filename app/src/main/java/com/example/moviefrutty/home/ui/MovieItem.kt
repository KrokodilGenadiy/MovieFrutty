package com.example.moviefrutty.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviefrutty.R
import com.example.moviefrutty.home.data.Movie
import com.example.moviefrutty.ui.theme.MovieFruttyTheme


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    val icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.movie_test),
                contentDescription = "Movie image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
            )
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Title",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = "Just in case someone wonders: the method presented here will work IF you have ever had that commit IN your local machine (either you commit in the local repository or fetch the change). If you want to restore a commit someone else has pushed & deleted on GitHub / GitLab server, take a look at the API of GitHub, there will be API to help create a branch from the old commit",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieFruttyTheme {
        MovieCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            "title",
            "description",
            onClick = {}
        )
    }

}
