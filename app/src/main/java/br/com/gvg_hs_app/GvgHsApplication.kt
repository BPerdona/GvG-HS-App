package br.com.gvg_hs_app

import android.app.Application
import br.com.gvg_hs_app.data.local.CardsDatabase
import br.com.gvg_hs_app.data.repository.CardRepository

class GvgHsApplication: Application() {

    private val database: CardsDatabase by lazy {
        CardsDatabase.getInstance(this)
    }

    val repository: CardRepository by lazy{
        CardRepository(database.cardDao())
    }
}