package com.ruavee.mentalcountingapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ruavee.mentalcountingapp.ui.screens.MainScreen
import com.ruavee.mentalcountingapp.ui.screens.TimerScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onNavigateToTimer = {
                    if (navController.currentDestination?.route != "timer") {
                        navController.navigate("timer") {
                            launchSingleTop = true
                            popUpTo("main") { inclusive = false }
                        }
                    }
                }
            )
        }
        composable("timer") {
            TimerScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}