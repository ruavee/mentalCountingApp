@file:OptIn(ExperimentalMaterial3Api::class)

package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onNavigateToTimer: () -> Unit
) {
    val problem = viewModel.problemText
    val message = viewModel.resultMessage
    val count = viewModel.correctCount
    val input = viewModel.userInput
    val isChecking = viewModel.isChecking
    val difficultyState = rememberSaveable { mutableFloatStateOf(1f) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
        ) {
            Button(
                modifier = Modifier
                    .align(TopEnd)
                    .wrapContentSize(),
                onClick = onNavigateToTimer,
                shape = RoundedCornerShape(7.dp),
                contentPadding = PaddingValues(vertical = 3.dp, horizontal = 15.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.switchTimerMode),
                    style = Typography.headlineMedium.copy(
                        fontFamily = FontFamily(Font(R.font.cmunbx)),
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontSize = 20.sp
                    )
                )
            }
        }

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

        Text(
            text = stringResource(id = R.string.difficultyHint),
            style = Typography.bodyLarge.copy(
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            textAlign = TextAlign.Center
        )

        Slider (
            value = difficultyState.floatValue,
            onValueChange = { new ->
                difficultyState.floatValue = new
                viewModel.onDifficultyChange(new.toInt())
            },
            valueRange = 1f..3f,
            steps = 1,
            modifier = Modifier
                .width(215.dp)
                .height(40.dp)
                .padding(top = 10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.easyLevel),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                color = MaterialTheme.colorScheme.primary
                )
            Spacer(modifier = Modifier.width(35.dp))
            Text(
                text = stringResource(id = R.string.midLevel),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                color = MaterialTheme.colorScheme.primary
                )
            Spacer(modifier = Modifier.width(35.dp))
            Text(
                text = stringResource(id = R.string.hardLevel),
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.cmunrm)),
                color = MaterialTheme.colorScheme.primary
                )
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
                modifier = Modifier
                    .height(56.dp),
                onClick = { viewModel.onCheck() },
                enabled = !isChecking
            ) {
                Text(
                    text = stringResource(R.string.equals),
                    color = MaterialTheme.colorScheme.inversePrimary,
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
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                onClick = { viewModel.correctCount = 0 },
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
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
                        color = MaterialTheme.colorScheme.inversePrimary,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        NumberKeyboard(
            onKeyPress = { viewModel.onKeyboardInput(it) }
        )
    }
}
