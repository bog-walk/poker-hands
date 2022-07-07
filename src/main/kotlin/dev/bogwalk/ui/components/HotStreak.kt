package dev.bogwalk.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import dev.bogwalk.model.ui.style.*
import dev.bogwalk.ui.style.*
import ui.style.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HotStreak(streak: Int) {
    val transition = updateTransition(targetState = streak)

    val streakColor by transition.animateColor(
        transitionSpec = { tween(STREAK_ANIM_DURATION, easing = FastOutSlowInEasing) }
    ) { count ->
        when (count) {
            0 -> MaterialTheme.colors.onError
            else -> MaterialTheme.colors.secondaryVariant // yellow
        }
    }

    // AnimatedContent below uses this as the targetState, which forces the animation cycle through
    // all integers between initialState and updatedState (0) when a streak is lost.
    // This creates the observable effect of the streak value decrementing quickly rather than
    // simply changing to zero.
    val streakCount by transition.animateInt(
        transitionSpec = {
            if (targetState == 0) {
                tween(STREAK_ANIM_DURATION, easing = FastOutSlowInEasing)
            } else {
                snap()
            }
        }
    ) { it }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(STREAK_ICON),
            contentDescription = STREAK_DESCRIPTION,
            modifier = Modifier.requiredSize(iconSize),
            tint = streakColor
        )
        Spacer(modifier = Modifier.width(componentPadding))
        // Sourced from the official Jetpack Compose Animation guide & adapted to create a quick
        // scroll decrement animation when targetState < initialState.
        // https://developer.android.com/jetpack/compose/animation
        AnimatedContent(
            targetState = streakCount,
            transitionSpec = {
                if (targetState > initialState) {
                    // count slides upwards by 1 increment to replace previous count
                    slideInVertically { y -> y } + fadeIn() with
                            slideOutVertically { y -> -y } + fadeOut()
                } else {
                    // count slides downwards through all numbers between initialState and 0
                    slideInVertically { y -> -y } + fadeIn() with
                            slideOutVertically { y -> y } + fadeOut()
                }.using(
                    // if not disabled, slide animation will not be displayed out of bounds
                    SizeTransform(clip = false)
                )
            }
        ) { targetStreak ->
            Text(
                text = "$targetStreak",
                color = streakColor,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Preview
@Composable
private fun HotStreakPreview() {
    PokerHandsTheme {
        Column {
            HotStreak(0)
            HotStreak(5)
            HotStreak(100)
        }
    }
}