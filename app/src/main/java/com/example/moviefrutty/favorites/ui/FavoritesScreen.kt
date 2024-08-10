package com.example.moviefrutty.favorites.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviefrutty.Greeting
import com.example.moviefrutty.core.ui.BottomNavigation

@Composable
fun FavoritesScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { BottomNavigation() }) { innerPadding ->
        Greeting(
            name = "Favorites screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}