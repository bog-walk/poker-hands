package dev.bogwalk

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.poker_icon
import dev.bogwalk.poker_hands.generated.resources.window_title
import dev.bogwalk.ui.PokerHandsApp
import dev.bogwalk.ui.style.PokerHandsTheme
import dev.bogwalk.ui.style.windowHeight
import dev.bogwalk.ui.style.windowWidth
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(windowWidth, windowHeight)),
        title = stringResource(Res.string.window_title),
        icon = painterResource(Res.drawable.poker_icon),
        resizable = false
    ) {
        PokerHandsTheme {
            PokerHandsApp()
        }
    }
}