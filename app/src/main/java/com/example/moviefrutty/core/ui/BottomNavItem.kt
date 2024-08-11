package com.example.moviefrutty.core.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val selectedIcon: ImageVector,val unselectedIcon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, Icons.Outlined.Home,"Home")
    object Favorites : BottomNavItem("favorites", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder,"Favorites")
    object Notifications : BottomNavItem("notifications", Icons.Filled.Notifications, Icons.Outlined.Notifications,"Notifications")
    object Settings : BottomNavItem("settings", Icons.Filled.Settings, Icons.Outlined.Settings,"Settings")
}