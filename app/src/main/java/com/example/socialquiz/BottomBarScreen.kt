package com.example.socialquiz

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector,
    val selectedIcon : ImageVector
) {
    object Home : BottomBarScreen("home", "Home", Icons.Outlined.Home,Icons.Filled.Home)
    object Profile : BottomBarScreen("profile", "Profile",  Icons.Outlined.Person,Icons.Filled.Person)
    object Notes : BottomBarScreen("notes", "Notes",  Icons.Outlined.Notifications,Icons.Filled.Notifications)

    object Items {
      val lists  = listOf(Home, Profile, Notes)
    }
}
