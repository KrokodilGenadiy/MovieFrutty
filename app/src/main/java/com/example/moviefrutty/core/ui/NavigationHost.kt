package com.example.moviefrutty.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.moviefrutty.favorites.ui.FavoritesScreen
import com.example.moviefrutty.home.ui.HomeScreen
import com.example.moviefrutty.notifications.ui.NotificationScreen
import com.example.moviefrutty.settings.ui.SettingsScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen() }
        composable(BottomNavItem.Favorites.route) { FavoritesScreen() }
        composable(BottomNavItem.Notifications.route) { NotificationScreen() }
        composable(BottomNavItem.Settings.route) { SettingsScreen() }
    }
}