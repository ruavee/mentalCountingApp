package com.ruavee.mentalcountingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import com.ruavee.mentalcountingapp.ui.theme.MentalCountingAppTheme
import com.ruavee.mentalcountingapp.navigation.NavGraph

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MentalCountingAppTheme {
                NavGraph()
            }
        }
    }
}