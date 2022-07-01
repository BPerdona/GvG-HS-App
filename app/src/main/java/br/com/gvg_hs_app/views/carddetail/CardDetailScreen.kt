package br.com.gvg_hs_app.views.carddetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.gvg_hs_app.data.domain.Card

@Composable
fun CardDetailScreen(
    card: Card
){
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 2.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "${card.name}",
                style = MaterialTheme.typography.h5
                    .copy(color = rarityColor(card.rarity), fontWeight = FontWeight.Bold)
            )
        }
    }
}

fun rarityColor(type: String): Color {
    if(type == "Rare")
        return Color(0xFF5b7deb)

    if(type == "Epic")
        return Color(0xFFb65beb)

    if(type == "Legendary")
        return Color(0xFFebb15b)

    return Color(0xFFffffff)
}