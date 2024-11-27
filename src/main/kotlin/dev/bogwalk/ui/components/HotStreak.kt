package dev.bogwalk.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.bogwalk.poker_hands.generated.resources.Res
import dev.bogwalk.poker_hands.generated.resources.materials_fire_icon
import dev.bogwalk.poker_hands.generated.resources.streak_cd
import dev.bogwalk.ui.style.PokerHandsTheme
import dev.bogwalk.ui.style.STREAK_ANIM_DURATION
import dev.bogwalk.ui.style.componentPadding
import dev.bogwalk.ui.style.iconSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun HotStreak(streak: Int) {
    val transition = updateTransition(targetState = streak)

    val streakColor by transition.animateColor(
        transitionSpec = { tween(STREAK_ANIM_DURATION, easing = FastOutSlowInEasing) }
    ) { count ->
        when (count) {
            0 -> MaterialTheme.colorScheme.onError
            else -> MaterialTheme.colorScheme.tertiary
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
            painter = painterResource(Res.drawable.materials_fire_icon),
            contentDescription = stringResource(Res.string.streak_cd),
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
                    (slideInVertically { y -> y } + fadeIn()) togetherWith
                            slideOutVertically { y -> -y } + fadeOut()
                } else {
                    // count slides downwards through all numbers between initialState and 0
                    slideInVertically { y -> -y } + fadeIn() togetherWith
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
                style = MaterialTheme.typography.titleMedium
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