package com.ruavee.mentalcountingapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ruavee.mentalcountingapp.ui.screens.HomeScreen
import com.ruavee.mentalcountingapp.ui.screens.MainScreen
import com.ruavee.mentalcountingapp.ui.screens.ProfileScreen
import com.ruavee.mentalcountingapp.ui.screens.StatsScreen
import com.ruavee.mentalcountingapp.ui.screens.TimerScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val bottomBarRoutes = listOf(
        Screen.Home.route,
        Screen.Stats.route,
        Screen.Profile.route
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in bottomBarRoutes
    Scaffold(
        bottomBar = { if (showBottomBar) BottomBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToEndless = { navController.navigate("endless") },
                    onNavigateToTimer = { navController.navigate("timer") },
                    onNavigateToEquations = { /* TODO */ }
                )
            }
            composable("endless") {
                MainScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            composable("timer") {
                TimerScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable(Screen.Stats.route) {
                StatsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    val items = listOf(Screen.Home, Screen.Stats, Screen.Profile)
    val backStack by navController.currentBackStackEntryAsState()
    val current = backStack?.destination?.route
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { androidx.compose.material3.Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = current == screen.route,
                onClick = {
                    if (current != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
