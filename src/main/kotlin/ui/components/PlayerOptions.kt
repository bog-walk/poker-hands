package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Winner

@Composable
fun PlayerOptions(player: Winner) {
    Row(
        modifier = Modifier.padding(15.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PickButton(player)
        Spacer(modifier = Modifier.width(15.dp))
        InfoButton()
    }
}

@Composable
fun PickButton(player: Winner) {
    OutlinedButton(
        onClick = {},
        shape = RoundedCornerShape(50),
        border = BorderStroke(3.dp, Color.DarkGray),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.White,
            contentColor = Color.DarkGray
        )
    ) {
        Text(
            text = "PLAYER ${player.ordinal + 1}",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InfoButton() {
    IconButton(
        onClick = {},
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            painterResource("info_outline.svg"),
            contentDescription = "Info",
            tint = Color.DarkGray
        )
    }
}

@Preview
@Composable
fun PlayerOptionsPreview() {
    PlayerOptions(Winner.PLAYER1)
}



