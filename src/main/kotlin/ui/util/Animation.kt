package ui.util

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

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
                        -1 -> MaterialTheme.colors.error
                        1 -> MaterialTheme.colors.secondary
                        else -> defaultColor
                    }
                }
                delay(delayChange)
            }
        }
    }
}