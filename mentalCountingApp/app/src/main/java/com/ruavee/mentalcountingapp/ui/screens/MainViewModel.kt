package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruavee.mentalcountingapp.domain.usecase.GenerateProblemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.objecthunter.exp4j.ExpressionBuilder
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class MainViewModel @Inject constructor(
    private val generateProblemUseCase: GenerateProblemUseCase
) : ViewModel() {
    var problemText by mutableStateOf("")
        private set
    var userInput by mutableStateOf("")
    var resultMessage by mutableStateOf<String?>(null)
    var correctCount by mutableIntStateOf(0)
    var difficulty by mutableIntStateOf(1)
    var isChecking by mutableStateOf(false)

    init {
        loadNewProblem()
    }

    fun loadNewProblem() {
        problemText = generateProblemUseCase(difficulty)
        resultMessage = null
        userInput = ""
        isChecking = false

    }

    fun onCheck() {
        if (isChecking) return
        isChecking = true

        val expected = ExpressionBuilder(problemText.replace('–', '-')).build().evaluate()
        val input = userInput.replace('–', '-').toDoubleOrNull()
        val epsilon = 0.0001

        if (input != null && abs(input - expected) < epsilon) {
            resultMessage = "Верно!"
            correctCount++
        } else {
            resultMessage = "Неверно! Ответ: ${expected.toInt()}"
        }

        viewModelScope.launch {
            delay(1000)
            loadNewProblem()
        }
    }

    fun onKeyboardInput(key: String) {
        if (isChecking) return
        if (key == "DEL") userInput = userInput.dropLast(1)
        else userInput += key
    }

    fun onDifficultyChange(newDifficulty: Int) {
        difficulty = newDifficulty
        loadNewProblem()
    }
}