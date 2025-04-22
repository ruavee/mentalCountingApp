package com.ruavee.mentalcountingapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProblemDisplay(
    text: String,
    isError: Boolean = false
) {
    Surface(
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth(),
        color = if (isError) MaterialTheme.colorScheme.errorContainer else MaterialTheme.colorScheme.surface
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = if (isError) FontWeight.Bold else FontWeight.Normal,
                fontSize = 24.sp,
                color = if (isError) MaterialTheme.colorScheme.onErrorContainer else MaterialTheme.colorScheme.onSurface
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}