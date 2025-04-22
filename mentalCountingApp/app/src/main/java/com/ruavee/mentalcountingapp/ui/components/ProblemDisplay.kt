package com.ruavee.mentalcountingapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruavee.mentalcountingapp.R

@Composable
fun ProblemDisplay(
    text: String,
    correct: Int = 0
) {
    Surface(
        tonalElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(BorderStroke(2.dp, MaterialTheme.colorScheme.outlineVariant)),
        color = Color.Transparent,
    ) {
        Box (
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = text,
                lineHeight = 28.sp,
                maxLines = 2,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.cmunrm)),
                    textAlign = TextAlign.Center,
                    color = when (correct) {
                        -1 -> colorResource(R.color.incorrect)
                        1 -> colorResource(R.color.correct)
                        else -> MaterialTheme.colorScheme.onSurface
                    }
                ),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center),
            )
        }
    }
}