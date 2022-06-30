package br.com.gvg_hs_app.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.gvg_hs_app.R
import br.com.gvg_hs_app.data.domain.Card
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardListScreen(
    cardsViewModel: CardsViewModel
){
    val cardList by cardsViewModel.cardList.observeAsState(listOf())
    val filter by cardsViewModel.filter.observeAsState("")

    Column() {
        SearchFilter(
            filter,
            cardsViewModel::updateFilter
        )
        CardList(cardList)
    }
}

@Composable
fun SearchFilter(
    word: String,
    onFilterChange: (String) -> Unit
){
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        label = {
                Row(){
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    Text("Search")
                }
        },
        value = word,
        onValueChange = onFilterChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color(0xFFCC7b16),
            focusedIndicatorColor = Color(0xFFCC7b16)
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardList(
    cardList: List<Card>
){
    LazyVerticalGrid(
        modifier = Modifier.background(Color.Black),
        cells = GridCells.Fixed(2),
    ){
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
            .padding(5.dp),
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