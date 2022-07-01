package br.com.gvg_hs_app.views.cardslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.gvg_hs_app.R
import br.com.gvg_hs_app.data.domain.Card
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardListScreen(
    cardsViewModel: CardsViewModel,
    navController: NavController
){
    val cardList by cardsViewModel.cardList.observeAsState(listOf())
    val filter by cardsViewModel.filter.observeAsState("")

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 2.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "GvG HearthStone Cards",
                style = MaterialTheme.typography.h5.copy(
                    color = Color(0xFFfce4c5),
                    fontWeight = FontWeight.Bold
                )
            )
        }
        SearchFilter(
            filter,
            cardsViewModel::updateFilter
        )
        CardList(
            cardList,
            navController)
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
            .padding(bottom = 6.dp, top = 3.dp, start = 3.dp, end = 3.dp),
        label = {
                Row(){
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    Text("Search")
                }
        },
        value = word,
        onValueChange = onFilterChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color(0xFFfcc379),
            focusedIndicatorColor = Color(0xFFfcc379)
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardList(
    cardList: List<Card>,
    navController: NavController
){
    LazyVerticalGrid(
        modifier = Modifier.background(Color.Black),
        cells = GridCells.Fixed(2),
    ){
        items(cardList){
            CardItem(it){
                navController.navigate("cardList/${it.cardId}")
            }
        }
    }
}

@Composable
fun CardItem(
    card: Card,
    cardDetail: () -> Unit
){
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(5.dp)
            .clickable { cardDetail() },
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