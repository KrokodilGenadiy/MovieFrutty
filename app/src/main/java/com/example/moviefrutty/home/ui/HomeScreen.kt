package com.example.moviefrutty.home.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.moviefrutty.home.data.entity.Movie
import com.example.moviefrutty.home.domain.events.MoviesEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val searchQuery by viewModel.searchQuery.collectAsState()
    val movies by viewModel.movies.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(scrollBehavior,searchQuery,viewModel::onSearchTextChange)
        },
    ) { innerPadding ->
        MovieList(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp), viewModel.events, isSearching, movies
        )
    }
}

@Composable
fun MovieList(
    modifier: Modifier,
    events: Flow<MoviesEvent.Error>,
    isSearching: Boolean,
    movies: List<Movie>
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {
            events.collectLatest { event ->
                Toast.makeText(context, event.error.asString(context), Toast.LENGTH_SHORT).show()
            }
        }
    }
    if (isSearching) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        LazyColumn(
            modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(movies,
                key = { movie ->
                    movie.id
                }) {
                MovieCard(
                    title = it.title,
                    description = it.overview,
                    poster = it.posterPath,
                    onClick = {}
                )
            }
        }
    }

}