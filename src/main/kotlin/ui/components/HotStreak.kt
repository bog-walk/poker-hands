package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HotStreak(modifier: Modifier, count: Int = 0) {
    Row(
        modifier = modifier.padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource("hot_streak.svg"),
            contentDescription = "Flame icon",
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(
            text = count.toString(),
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun HotStreakPreview() {
    HotStreak(modifier = Modifier, 5)
}






