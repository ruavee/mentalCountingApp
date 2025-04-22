package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruavee.mentalcountingapp.ui.components.NumberKeyboard
import com.ruavee.mentalcountingapp.ui.components.ProblemDisplay

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToTimer: () -> Unit
) {
    val problem = viewModel.problemText
    val message = viewModel.resultMessage
    val count = viewModel.correctCount
    val input = viewModel.userInput
    val isChecking = viewModel.isChecking

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ProblemDisplay(
            text = message ?: problem,
            isError = message?.startsWith("Неверно") == true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { viewModel.userInput = it },
            singleLine = true,
            enabled = !isChecking,
            modifier = Modifier.fillMaxWidth(0.6f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onCheck() },
            enabled = !isChecking
        ) {
            Text("Проверить")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Правильных ответов: $count")

        Spacer(modifier = Modifier.weight(1f))

        NumberKeyboard(
            onKeyPress = { viewModel.onKeyboardInput(it) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(
            onClick = onNavigateToTimer
        ) {
            Text("Режим с таймером")
        }
    }
}
