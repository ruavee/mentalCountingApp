package com.ruavee.mentalcountingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumberKeyboard(
    onKeyPress: (String) -> Unit,
) {
    val keys = listOf(
        listOf("1","2","3"),
        listOf("4","5","6"),
        listOf("7","8","9"),
        listOf("-","0","DEL")
    )
    Column {
        keys.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                row.forEach { key ->
                    Button(
                        onClick = { onKeyPress(key) },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f),
                    ) {
                        Text(key)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}