package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ui.style.*

@Composable
fun HotStreak(count: Int) {
    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(hotStreakIcon),
            contentDescription = hotStreakDescr,
            modifier = Modifier.requiredSize(iconSize),
            tint = if (count == 0) {
                PokerHandsTheme.lightColors.onSecondary
            } else {
                PokerHandsTheme.lightColors.secondaryVariant
            }
        )
        Spacer(modifier = Modifier.width(componentPadding))
        Text(
            text = count.toString(),
            style = PokerHandsTheme.typography.h6
        )
    }
}

@Preview
@Composable
fun HotStreakPreview() {
    HotStreak(5)
}






