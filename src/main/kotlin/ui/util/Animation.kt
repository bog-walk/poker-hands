package ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import ui.style.PokerColors

/**
 * Given any number of lists representing a mapping of the internal elements of the calling
 * Composable (e.g. 5 PokerCards in a PokerHand or 10 Texts in an InfoPanel), this produces
 * successive observable Color states for each element, which can be used to alter the element's
 * chosen Color property (border for Card, color for Text). Adding a delay allows each change to
 * be observed as if it were an animation.
 */
@Composable
fun produceHighlightState(
    listSize: Int,
    delayChange: Long,
    key: List<List<Int>>,
    defaultColor: Color = Color.Transparent
): State<List<Color>> {
    return produceState(
        initialValue = List(listSize) { defaultColor },
        key
    ) {
        if (key.isEmpty()) {
            value = List(listSize) { defaultColor }
        } else {
            for (nested in key) {
                value = nested.map { num ->
                    when (num) {
                        -1 -> PokerColors.error // red
                        1 -> PokerColors.secondary // green
                        else -> defaultColor
                    }
                }
                delay(delayChange)
            }
        }
    }
}