import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ui.PokerHandsApp

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(800.dp, 500.dp)),
        title = "Poker Hands",
        icon = painterResource("heart_black_24.png")
    ) {
        PokerHandsApp()
    }
}