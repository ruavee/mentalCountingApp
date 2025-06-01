import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ruavee.mentalcountingapp.ui.screens.HomeScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysAllModeCards_andClicksWork() {
        var endlessClicked = false
        var timerClicked = false
        var equationsClicked = false

        var endlessText = ""
        var timerText = ""
        var equationsText = ""

        composeTestRule.setContent {
            val context = LocalContext.current
            endlessText = context.getString(com.ruavee.mentalcountingapp.R.string.EndlessModeName)
            timerText = context.getString(com.ruavee.mentalcountingapp.R.string.TimerModeName)
            equationsText = context.getString(com.ruavee.mentalcountingapp.R.string.EquationsModeName)

            HomeScreen(
                onNavigateToTimer = { timerClicked = true },
                onNavigateToEndless = { endlessClicked = true },
                onNavigateToEquations = { equationsClicked = true }
            )
        }

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText(endlessText).assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText(timerText).assertIsDisplayed().performClick()
        composeTestRule.onNodeWithText(equationsText).assertIsDisplayed().performClick()

        assert(endlessClicked) { "Endless mode click should trigger callback" }
        assert(timerClicked) { "Timer mode click should trigger callback" }
        assert(equationsClicked) { "Equations mode click should trigger callback" }
    }
}
