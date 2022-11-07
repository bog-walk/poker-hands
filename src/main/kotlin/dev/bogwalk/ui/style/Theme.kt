package dev.bogwalk.ui.style

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

private val PokerBlue = Color(0xff9ab8ef)
private val PokerGreen = Color(0xffd4fa81)
private val PokerRed = Color(0xfffca5a5)
private val PokerYellow = Color(0xffffc400)

val PokerColors = darkColorScheme(
    primary = PokerBlue,
    onPrimary = Color.DarkGray,
    secondary = PokerGreen,
    onSecondary = Color.White,
    tertiary = PokerYellow,
    background = Color.DarkGray,
    onBackground = Color.White,
    surface = Color.DarkGray,
    onSurface = Color.White,
    error = PokerRed,
    onError = Color.Gray
)

private val PokerTypography = Typography(
    displaySmall = TextStyle(
        fontSize = 37.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.Monospace,
        letterSpacing = .5.sp,
        textAlign = TextAlign.Center,
        lineHeight = 37.sp
    ),
    titleMedium = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = .5.sp,
        textAlign = TextAlign.Center
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Monospace,
        letterSpacing = .5.sp,
        textAlign = TextAlign.Center
    )
)

@Composable
fun PokerHandsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PokerColors,
        typography = PokerTypography
    ) {
        Surface(content = content)
    }
}