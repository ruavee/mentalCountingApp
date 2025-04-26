package com.ruavee.mentalcountingapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home    : Screen("home",    "Главная",    Icons.Filled.Home)
    object Stats   : Screen("stats",   "Статистика", Icons.Filled.BarChart)
    object Profile : Screen("profile", "Профиль",    Icons.Filled.Person)
}