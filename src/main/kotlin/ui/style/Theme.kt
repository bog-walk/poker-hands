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

private val PokerBlue = Color(0xff8197fa)
private val PokerRed = Color(0xfffaa881)
private val PokerGreen = Color(0xffd4fa81)
private val PokerYellow = Color(0xfffae481)

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
    onError = Color.White
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
        color = PokerYellow,
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

object PokerHandsTheme {
    val colors = PokerColors
    val typography = PokerTypography
    val shapes = PokerShapes
}

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