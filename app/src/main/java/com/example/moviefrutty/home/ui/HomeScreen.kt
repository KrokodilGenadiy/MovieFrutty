package com.example.moviefrutty.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.moviefrutty.home.data.Movie
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        MovieList(modifier = Modifier
            .padding(innerPadding)
            .padding(8.dp),viewModel)
    }
}

@Composable
fun MovieList(modifier: Modifier,viewModel: HomeScreenViewModel) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.loadFilmsFromApi(1)

        coroutineScope.launch {
            viewModel.events.collectLatest { event ->
                Toast.makeText(context,event.error.asString(context),Toast.LENGTH_SHORT).show()
            }
        }
    }

    
    val movies = viewModel.moviesFlow.collectAsState(initial = emptyList())
    LazyColumn(modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(movies.value) {
            MovieCard(
                title = it.title,
                description = it.overview,
                poster = it.posterPath,
                onClick = {}
            )
        }
    }
}