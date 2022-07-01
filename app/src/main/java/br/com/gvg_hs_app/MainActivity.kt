package br.com.gvg_hs_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.gvg_hs_app.data.domain.Card
import br.com.gvg_hs_app.ui.theme.GvGHSAppTheme
import br.com.gvg_hs_app.views.carddetail.CardDetailScreen
import br.com.gvg_hs_app.views.cardslist.CardListScreen
import br.com.gvg_hs_app.views.cardslist.CardVMFactory
import br.com.gvg_hs_app.views.cardslist.CardsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardsViewModel by viewModels<CardsViewModel>(){
            CardVMFactory(
                (this.applicationContext as GvgHsApplication).repository
            )
        }

        setContent {
            GvGHSAppTheme(darkTheme = true) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GvGHeroes(cardsViewModel)
                }
            }
        }
    }
}

@Composable
fun GvGHeroes(
    cardsViewModel: CardsViewModel
){
    val navController = rememberNavController()
    Scaffold() {
        NavHost(navController = navController, startDestination = "cardList"){
            composable(route = "cardList"){
                CardListScreen(
                    cardsViewModel,
                    navController
                )
            }
            composable(
                route = "cardList/{cardId}",
                arguments = listOf(navArgument("cardId"){
                    defaultValue = "None"
                    type = NavType.StringType
                })
            ){
                val cardId = it.arguments?.getString("cardId") ?: "None"
                val card = cardsViewModel.getCardById(cardId)
                CardDetailScreen(card)
            }
        }
    }
}