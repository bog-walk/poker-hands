package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Winner

@Composable
fun PickButton(player: Winner) {
    Button(
        onClick = {},
        modifier = Modifier.padding(20.dp).wrapContentWidth(),
        shape = RoundedCornerShape(100)
    ) {
        Text(
            text = "PLAYER ${player.ordinal + 1}",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PickButtonPreview() {
    PickButton(Winner.PLAYER2)
}