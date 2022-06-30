package br.com.gvg_hs_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.gvg_hs_app.ui.theme.GvGHSAppTheme
import br.com.gvg_hs_app.views.CardListScreen
import br.com.gvg_hs_app.views.CardVMFactory
import br.com.gvg_hs_app.views.CardsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cardsViewModel by viewModels<CardsViewModel>(){
            CardVMFactory(
                (this.applicationContext as GvgHsApplication).repository
            )
        }

        setContent {
            GvGHSAppTheme {
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
    CardListScreen(cardsViewModel)
}