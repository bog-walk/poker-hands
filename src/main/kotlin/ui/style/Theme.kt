package ui.style

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
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

private val PokerColors = darkColors(
    primary = Blue200,
    primaryVariant = Blue500,
    secondary = Green500,
    secondaryVariant = Yellow800,
    background = Color.DarkGray,
    surface = Color.DarkGray,
    error = Red800,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)

val buttonColor = ButtonDefaults.buttonColors(
    backgroundColor = PokerHandsTheme.colors.primary,
    contentColor = PokerHandsTheme.colors.onPrimary,
    disabledBackgroundColor = PokerHandsTheme.colors.secondary.copy(alpha = 0.12f)
        .compositeOver(PokerHandsTheme.colors.surface),
    disabledContentColor = PokerHandsTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
)

val buttonColorError = ButtonDefaults.buttonColors(
    disabledBackgroundColor = PokerHandsTheme.colors.error.copy(alpha = 0.12f)
        .compositeOver(PokerHandsTheme.colors.surface),
    disabledContentColor = PokerHandsTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
)

private val PokerTypography = Typography(
    defaultFontFamily = FontFamily.Monospace,
    h4 = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        lineHeight = 40.sp
    ),
    body1 = TextStyle(
        color = Yellow800,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
    ),
    button = TextStyle(
        fontSize = 14.sp,
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
        Surface(
            modifier = Modifier.fillMaxSize(),
            content = content
        )
    }
}