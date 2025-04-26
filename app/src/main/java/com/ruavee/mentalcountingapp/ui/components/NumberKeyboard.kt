package com.ruavee.mentalcountingapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruavee.mentalcountingapp.R

@Composable
fun NumberKeyboard(
    onKeyPress: (String) -> Unit,
) {
    val keys = listOf(
        listOf(stringResource(R.string._1),stringResource(R.string._2),stringResource(R.string._3)),
        listOf(stringResource(R.string._4),stringResource(R.string._5),stringResource(R.string._6)),
        listOf(stringResource(R.string._7),stringResource(R.string._8),stringResource(R.string._9)),
        listOf(stringResource(R.string.minus),stringResource(R.string._0),stringResource(R.string.deleteSymbol))
    )
    Column (
        verticalArrangement = Arrangement.Top
    ) {
        keys.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                row.forEach { key ->
                    Button(
                        onClick = { onKeyPress(key) },
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                    ) {
                        Text(
                            text = key,
                            fontFamily = FontFamily(Font(R.font.cmunrm)),
                            fontSize = 32.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}