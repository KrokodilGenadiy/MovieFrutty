package com.example.moviefrutty.home.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviefrutty.Greeting
import com.example.moviefrutty.core.ui.BottomNavigation

@Composable
fun HomeScreen() {
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { BottomNavigation() }) { innerPadding ->
        Greeting(
            name = "Home screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}