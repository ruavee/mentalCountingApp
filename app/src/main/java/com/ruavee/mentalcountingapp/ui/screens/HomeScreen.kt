package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruavee.mentalcountingapp.R

@Composable
fun HomeScreen(
    onNavigateToTimer: () -> Unit,
    onNavigateToEndless: () -> Unit,
    onNavigateToEquations: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(Modifier.height(0.dp))

        Text(
            text = stringResource(R.string.AppLongName),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily(Font(R.font.cmunrm))
        )

        Spacer(Modifier.height(0.dp))

        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.chooseMode),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            Spacer(Modifier.height(24.dp))

            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ModeCard(
                    stringResource(R.string.EndlessModeName),
                    stringResource(R.string.EndlessModeHint),
                    onNavigateToEndless
                )
                Spacer(Modifier.height(12.dp))
                ModeCard(
                    stringResource(R.string.TimerModeName),
                    stringResource(R.string.TimerModeHint),
                    onNavigateToTimer
                )
                Spacer(Modifier.height(12.dp))
                ModeCard(
                    stringResource(R.string.EquationsModeName),
                    stringResource(R.string.EquationsModeHint),
                    onNavigateToEquations
                )
            }
        }

        Spacer(modifier = Modifier.height(0.dp))
        Spacer(modifier = Modifier.height(0.dp))
        Spacer(modifier = Modifier.height(0.dp))
    }
}

@Composable
private fun ModeCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(320.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily(Font(R.font.cmunbx))
            )
            Text(
                text = description,
                modifier = Modifier.padding(start=20.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                fontStyle = FontStyle.Italic
            )
        }
    }
}
