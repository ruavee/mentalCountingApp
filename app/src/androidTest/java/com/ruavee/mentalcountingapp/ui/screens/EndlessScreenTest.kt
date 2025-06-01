package com.ruavee.mentalcountingapp.ui.screens

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.ruavee.mentalcountingapp.R
import io.mockk.*
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockViewModel = mockk<MainViewModel>(relaxed = true).apply {
        every { problemText } returns "1 + 1"
        every { resultMessage } returns null
        every { correctCount } returns 5
        every { userInput } returns ""
        every { isChecking } returns false
        every { onDifficultyChange(any()) } just Runs
        every { onCheck() } just Runs
        every { onKeyboardInput(any()) } just Runs
        every { correctCount = any() } just Runs
        every { userInput = any() } just Runs
    }

    @Test
    fun mainScreen_displaysElementsAndHandlesClicks() {
        var mainMenuText = ""

        composeTestRule.setContent {
            val context = LocalContext.current
            mainMenuText = context.getString(R.string.mainMenu)

            MainScreen(
                viewModel = mockViewModel,
                onNavigateBack = {}
            )
        }

        // Проверка, что отображается текст проблемы
        composeTestRule.onNodeWithText("1 + 1")
            .assertIsDisplayed()

        // Проверка, что отображается кнопка Назад
        composeTestRule.onNodeWithText(mainMenuText)
            .assertIsDisplayed()
            .performClick()

        // Проверка отображения счётчика верных ответов
        composeTestRule.onNodeWithText("Верно: 5").assertIsDisplayed()

        // Проверяем кнопку equals вызывает onCheck
        composeTestRule.onNodeWithText("=")
            .assertIsEnabled()
            .performClick()

        verify { mockViewModel.onCheck() }
    }

    @Test
    fun mainScreen_infoDialogAppearsAndDismisses() {
        composeTestRule.setContent {
            MainScreen(viewModel = mockViewModel, onNavigateBack = {})
        }

        // Нажимаем на иконку Info
        composeTestRule.onNodeWithContentDescription("Что такое уровень?")
            .assertIsDisplayed()
            .performClick()

        // Проверяем, что диалог с инфой появился
        composeTestRule.onNodeWithTag("infoDialog")
            .assertIsDisplayed()

        // Нажимаем кнопку Ок в диалоге
        composeTestRule.onNodeWithText("Ок")
            .assertIsDisplayed()
            .performClick()

        // Диалог должен исчезнуть
        composeTestRule.onNodeWithTag("infoDialog")
            .assertDoesNotExist()
    }

    @Test
    fun mainScreen_sliderChangesDifficulty() {
        val capturedDifficulty = mutableListOf<Int>()

        val vm = mockk<MainViewModel>(relaxed = true).apply {
            every { problemText } returns "dummy"
            every { resultMessage } returns null
            every { correctCount } returns 0
            every { userInput } returns ""
            every { isChecking } returns false
            every { onDifficultyChange(capture(capturedDifficulty)) } just Runs
            every { difficulty } returns 1 // если используется
        }

        composeTestRule.setContent {
            MainScreen(viewModel = vm, onNavigateBack = {})
        }

        composeTestRule.onNodeWithTag("slider")
            .assertExists()
            .assertIsDisplayed()
            .performSemanticsAction(SemanticsActions.SetProgress) {
                it(3f)
            }

        assert(capturedDifficulty.isNotEmpty()) { "onDifficultyChange не вызвался" }
        assert(capturedDifficulty.last() == 3) { "Ожидали 3, но получили ${capturedDifficulty.last()}" }
    }

}
