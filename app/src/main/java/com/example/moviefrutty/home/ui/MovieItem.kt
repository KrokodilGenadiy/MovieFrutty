package com.example.moviefrutty.home.ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviefrutty.R
import com.example.moviefrutty.core.data.api.ApiConstants
import com.example.moviefrutty.ui.theme.MovieFruttyTheme


@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    poster: String?,
    onClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    val icon = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .clickable {
                onClick()
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(ApiConstants.IMAGES_URL + "w780" + poster)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.movie_placeholder),
                contentDescription = stringResource(R.string.poster),
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
                Text(text = title,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = description,
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
            "string",
            onClick = {}
        )
    }

}
