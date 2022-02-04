package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Card
import model.Suit
import model.previewCards

@Composable
fun PokerCard(card: Card) {
    val suitPath = when (card.suit) {
        Suit.CLUB -> "club.svg"
        Suit.DIAMOND -> "diamond.svg"
        Suit.HEART -> "heart.svg"
        Suit.SPADE -> "spade.svg"
    }
    val valueColor = when (card.suit) {
        Suit.CLUB, Suit.SPADE -> Color.Black
        Suit.HEART, Suit.DIAMOND -> Color.Red
    }
    Card(
        modifier = Modifier.size(100.dp, 140.dp).padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White,
        contentColor = valueColor,
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = card.value,
                modifier = Modifier.padding(bottom = 5.dp),
                fontSize = 30.sp
            )
            Image(
                painter = painterResource(suitPath),
                contentDescription = "heart",
                modifier = Modifier.width(40.dp)
            )
        }
    }
}

@Preview
@Composable
fun PokerCardPreview() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        for (sample in previewCards) {
            PokerCard(sample)
        }
    }
}






