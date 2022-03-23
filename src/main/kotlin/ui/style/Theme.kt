package ui.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// original palette
//private val PokerBlue = Color(0xff8197fa)
//private val PokerRed = Color(0xfffaa881)
//private val PokerGreen = Color(0xffd4fa81)
//private val PokerYellow = Color(0xfffae481)
// 200-only palette (requires DarkGray on primary)
//private val PokerBlue = Color(0xffbfdbfe)
//private val PokerRed = Color(0xfffecaca)
//private val PokerGreen = Color(0xffd9f99d)
//private val PokerYellow = Color(0xfffef08a)
// 300-only palette
private val PokerBlue = Color(0xff93c5fd)
private val PokerRed = Color(0xfffca5a5)
// 300-green might be too saturated
//private val PokerGreen = Color(0xffbef264)
private val PokerGreen = Color(0xffd4fa81)
private val PokerYellow = Color(0xfffde047)

private val PokerColors = darkColors(
    primary = PokerBlue,
    secondary = PokerGreen,
    secondaryVariant = PokerYellow,
    background = Color.DarkGray,
    surface = Color.DarkGray,
    error = PokerRed,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Gray
)

private val PokerTypography = Typography(
    defaultFontFamily = FontFamily.Monospace,
    h4 = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center,
        lineHeight = 40.sp
    ),
    body1 = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    button = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
    )
)

private val PokerShapes = Shapes(
    small = RoundedCornerShape(50),
    medium = RoundedCornerShape(4.dp)
)

@Composable
fun PokerHandsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = PokerColors,
        typography = PokerTypography,
        shapes = PokerShapes
    ) {
        Surface(content = content)
    }
}