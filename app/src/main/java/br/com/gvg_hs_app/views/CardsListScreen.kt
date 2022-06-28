package br.com.gvg_hs_app.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.gvg_hs_app.R
import br.com.gvg_hs_app.data.Card
import coil.compose.AsyncImage
import coil.request.ImageRequest

private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com"

@Composable
fun CardListScreen(
    cardsViewModel: CardsViewModel
){
    val cardList by cardsViewModel.cardList.observeAsState(listOf())
    CardList(cardList)
}

@Composable
fun CardList(
    cardList: List<Card>
){
    LazyColumn(){
        items(cardList){
            CardItem(it)
        }
    }
}

@Composable
fun CardItem(
    card: Card
){
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(6.dp),
        elevation = 8.dp
    ) {
        Box(){
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(card.img)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = card.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}