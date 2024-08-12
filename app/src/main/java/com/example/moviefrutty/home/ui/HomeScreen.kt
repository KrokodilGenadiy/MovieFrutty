package com.example.moviefrutty.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviefrutty.home.data.Movie

@Composable
fun HomeScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MovieList( modifier = Modifier.padding(innerPadding).padding(horizontal = 8.dp))
    }
}

@Composable
fun MovieList(modifier: Modifier) {
    LazyColumn(modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(6) {
            MovieCard(
                title = "title",
                description = "description",
                onClick = {}
            )
        }
    }
}