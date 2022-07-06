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

private val PokerBlue = Color(0xff9ab8ef)
private val PokerGreen = Color(0xffd4fa81)
private val PokerRed = Color(0xfffca5a5)
private val PokerYellow = Color(0xffffc400)

val PokerColors = darkColors(
    primary = PokerBlue,
    secondary = PokerGreen,
    secondaryVariant = PokerYellow,
    background = Color.DarkGray,
    surface = Color.DarkGray,
    error = PokerRed,
    onPrimary = Color.DarkGray,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Gray
)

private val PokerTypography = Typography(
    defaultFontFamily = FontFamily.Monospace,
    h4 = TextStyle(
        fontSize = 37.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.5.sp,
        textAlign = TextAlign.Center,
        lineHeight = 37.sp
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