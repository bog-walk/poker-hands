import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import ui.PokerHandsApp
import ui.style.PokerHandsTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(size = DpSize(1000.dp, 500.dp)),
        title = "Poker Hands",
        icon = painterResource("heart_black_24.png"),
        resizable = false
    ) {
        PokerHandsTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                PokerHandsApp()
            }
        }
    }
}