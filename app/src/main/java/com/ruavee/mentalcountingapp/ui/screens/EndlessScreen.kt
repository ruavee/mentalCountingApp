@file:OptIn(ExperimentalMaterial3Api::class)

package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ruavee.mentalcountingapp.ui.components.NumberKeyboard
import com.ruavee.mentalcountingapp.ui.components.ProblemDisplay
import com.ruavee.mentalcountingapp.R
import com.ruavee.mentalcountingapp.ui.theme.*

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val problem = viewModel.problemText
    val message = viewModel.resultMessage
    val count = viewModel.correctCount
    val input = viewModel.userInput
    val isChecking = viewModel.isChecking
    val difficultyState = rememberSaveable { mutableFloatStateOf(1f) }
    var showInfo by remember { mutableStateOf(false) }

    if (showInfo) {
        AlertDialog(
            modifier = Modifier.testTag("infoDialog"),
            onDismissRequest = { showInfo = false },
            confirmButton = {
                TextButton(onClick = { showInfo = false }) {
                    Text(
                        text = "Ок",
                        fontFamily = FontFamily(Font(R.font.cmunrm)),
                        fontSize = 16.sp
                    )
                }
            },
            text = {
                Text(
                    text = """
                              Числа по модулю не превышают:
                              
                              - Лёгкий: 10
                              - Средний: 100
                              - Трудный: 1000
                            """
                        .trimIndent(),
                    fontFamily = FontFamily(Font(R.font.cmunrm)),
                    fontSize = 18.sp
                    )
            },
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
        ) {
            Button(
                modifier = Modifier
                    .align(TopStart)
                    .wrapContentSize(),
                onClick = onNavigateBack,
                shape = RoundedCornerShape(7.dp),
                contentPadding = PaddingValues(vertical = 3.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.mainMenu),
                    style = Typography.headlineMedium.copy(
                        fontFamily = FontFamily(Font(R.font.cmunbx)),
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 16.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.Name),
            style = Typography.headlineMedium.copy(
                fontFamily = FontFamily(Font(R.font.cmunbx)),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 30.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.difficultyHint),
                style = Typography.bodyLarge.copy(
                    fontFamily = FontFamily(Font(R.font.cmunrm)),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp
                ),
                modifier = Modifier.wrapContentWidth(),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = {
                    showInfo = true
                }
            ) {
                Icon(Icons.Default.Info, contentDescription = "Что такое уровень?")
            }
        }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Slider (
                value = difficultyState.floatValue,
                onValueChange = { new ->
                    difficultyState.floatValue = new
                    viewModel.onDifficultyChange(new.toInt())
                },
                valueRange = 1f..3f,
                steps = 1,
                modifier = Modifier
                    .width(225.dp)
                    .height(25.dp)
                    .testTag("slider")
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.width(270.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.easyLevel),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.cmunrm)),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.midLevel),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.cmunrm)),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = stringResource(R.string.hardLevel),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.cmunrm)),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        ProblemDisplay(
            text = message ?: problem,
            correct = if (message?.startsWith("Неверно") == true) {
                            -1
                        } else if (message?.startsWith("Верно") == true) {
                            1
                        } else {
                            0
                        }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row (modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            TextField(
                value = input,
                onValueChange = { viewModel.userInput = it },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(56.dp),
                textStyle = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.cmunrm))),
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.inputText),
                        style = TextStyle(
                            fontSize = 24.sp,
                            color = colorResource(R.color.textColorHint),
                            fontFamily = FontFamily(Font(R.font.cmunrm))
                            )
                        )
                },
                enabled = !isChecking,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                modifier = Modifier.height(56.dp),
                onClick = { viewModel.onCheck() },
                enabled = !isChecking,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Text(
                    text = stringResource(R.string.equals),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontFamily = FontFamily(Font(R.font.cmunbx)),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Row (
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Верно: $count",
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = { viewModel.correctCount = 0 },
                modifier = Modifier
                    .width(35.dp)
                    .height(35.dp)
                    .align(Alignment.CenterVertically),
                contentPadding = PaddingValues(all = 0.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 5.dp),
                    contentAlignment = Center
                ) {
                    Text(
                        text = stringResource(id = R.string.reCnt),
                        color = MaterialTheme.colorScheme.surface,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        NumberKeyboard(
            onKeyPress = { viewModel.onKeyboardInput(it) }
        )
    }
}
