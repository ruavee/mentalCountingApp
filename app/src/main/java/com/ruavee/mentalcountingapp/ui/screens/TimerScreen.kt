package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TimerScreen(
    onNavigateBack: () -> Unit
) {
    val isNavigating = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Здесь будет логика режима с таймером",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (!isNavigating.value) {
                        isNavigating.value = true
                        onNavigateBack()
                    }
                }
            ) {
                Text(text = "Вернуться")
            }
        }
    }
}

