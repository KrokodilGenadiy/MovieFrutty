package com.example.moviefrutty.notifications.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moviefrutty.Greeting

@Composable
fun NotificationScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            name = "Notification screen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}