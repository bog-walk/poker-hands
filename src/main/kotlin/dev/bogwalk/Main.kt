package dev.bogwalk.model

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import dev.bogwalk.ui.PokerHandsApp
import dev.bogwalk.model.ui.style.*
import dev.bogwalk.ui.style.*
import ui.style.*

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(windowWidth, windowHeight)),
        title = WINDOW_TITLE,
        icon = painterResource(POKER_HANDS_ICON),
        resizable = false
    ) {
        PokerHandsTheme {
            PokerHandsApp()
        }
    }
}