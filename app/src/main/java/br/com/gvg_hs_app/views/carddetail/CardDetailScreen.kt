package br.com.gvg_hs_app.views.carddetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.gvg_hs_app.R
import br.com.gvg_hs_app.data.domain.Card
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated

@Composable
fun CardDetailScreen(
    card: Card
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 30.dp, 0.dp, 2.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = card.name,
                style = MaterialTheme.typography.h1
                    .copy(color = rarityColor(card.rarity), fontWeight = FontWeight.Bold)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 4.dp, end = 4.dp, bottom = 10.dp)
        ){
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                    text = "Card Info:",
                    style = MaterialTheme.typography.body2
                        .copy(color = rarityColor(card.rarity), fontWeight = FontWeight.Bold)

                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Card Id: "+card.cardId,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Type: "+card.type,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Class: "+card.playerClass,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Rarity: "+card.rarity,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Artist: "+card.artist,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, bottom = 6.dp),
                    text = "Cost: "+card.cost.toString(),
                    fontWeight = FontWeight.Bold
                )
                if(card.type != "Spell"){
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 6.dp),
                        text = "Attack: "+card.attack.toString(),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp, bottom = 6.dp),
                        text = "Health: "+card.health.toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(){
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        alignment = Alignment.Center,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(card.img)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.cardback),
                        contentDescription = card.name,
                        contentScale = ContentScale.Fit,
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Flavor",
                style = MaterialTheme.typography.body2
                    .copy(color = rarityColor(card.rarity), fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = card.flavor,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 8.dp, end = 8.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ){
            imageButton(card = card)
        }
    }
}

@Composable
fun imageButton(
    card: Card
){
    val uriHandler = LocalUriHandler.current
    Button(
        modifier = Modifier
            .padding(top = 10.dp),
        onClick = {
            uriHandler.openUri(card.img)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = rarityColor(card.rarity),
            contentColor = Color.Black
        )
    ) {
        Text(
            text = "Download Card Image",
            style = MaterialTheme.typography.body1
                .copy(color = Color.Black, fontWeight = FontWeight.Bold)
        )
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