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

private val Blue200 = Color(0xff81d4fa)
private val Blue500 = Color(0xff03a9f4)
private val Red800 = Color(0xfff40303)
private val Green500 = Color(0xffd4fa81)
private val Yellow800 = Color(0xfffae481)

private val DarkColors = darkColors(
    primary = Blue200,
    primaryVariant = Blue500,
    secondary = Green500,
    secondaryVariant = Yellow800,
    background = Color.DarkGray,
    surface = Color.DarkGray,
    error = Red800,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.Black
)

private val LightColors = lightColors(
    primary = Blue500,
    primaryVariant = Blue500,
    secondary = Green500,
    secondaryVariant = Yellow800,
    background = Color.White,
    surface = Color.White,
    error = Red800,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val PokerTypography = Typography(
    defaultFontFamily = FontFamily.Monospace,
    h1 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 40.sp
    ),
    button = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    h6 = TextStyle(
        color = Yellow800,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    )
)

private val PokerShapes = Shapes(
    small = RoundedCornerShape(50),
    medium = RoundedCornerShape(4.dp)
)

object PokerHandsTheme {
    val lightColors = LightColors
    val darkColors = DarkColors
    val typography = PokerTypography
    val shape = PokerShapes
}

@Composable
fun PokerHandsTheme(
    darkMode: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkMode) DarkColors else LightColors,
        typography = PokerTypography,
        shapes = PokerShapes,
        content = content
    )
}







