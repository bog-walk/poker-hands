package ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ui.style.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HotStreak(streak: Int) {
    val transition = updateTransition(targetState = streak)
    val streakColor by transition.animateColor(
        transitionSpec = { tween(800, easing = FastOutSlowInEasing) }
    ) { count ->
        when (count) {
            0 -> PokerHandsTheme.colors.onError
            else -> PokerHandsTheme.colors.secondaryVariant
        }
    }
    // AnimatedContent below uses this as targetState to force animation cycle through all
    // numbers between initialState and updatedState (0) when streak is lost
    val streakCount by transition.animateInt(
        transitionSpec = {
            if (targetState == 0) {
                tween(800, easing = FastOutSlowInEasing)
            } else {
                snap()
            }
        }
    ) { it }

    Row(
        modifier = Modifier.padding(componentPadding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(hotStreakIcon),
            contentDescription = hotStreakDescr,
            modifier = Modifier.requiredSize(iconSize),
            tint = streakColor
        )
        Spacer(modifier = Modifier.width(componentPadding))
        AnimatedContent(
            targetState = streakCount,
            transitionSpec = {
                if (targetState > initialState) {
                    // count slides upwards by 1 increment
                    slideInVertically { y -> y } + fadeIn() with
                            slideOutVertically { y -> -y } + fadeOut()
                } else {
                    // count slides downwards through all number between initialState and 0
                    slideInVertically { y -> -y } + fadeIn() with
                            slideOutVertically { y -> y } + fadeOut()
                }.using(
                    // if not disabled, slide animation will not be displayed out of bounds
                    SizeTransform(clip = false)
                )
            }
        ) { targetStreak ->
            Text(
                text = targetStreak.toString(),
                color = streakColor,
                style = PokerHandsTheme.typography.body1
            )
        }
    }
}

@Preview
@Composable
fun HotStreakPreview() {
    PokerHandsTheme {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            HotStreak(0)
            HotStreak(5)
        }
    }
}