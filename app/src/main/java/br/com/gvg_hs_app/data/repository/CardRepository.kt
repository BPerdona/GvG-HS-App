package br.com.gvg_hs_app.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import br.com.gvg_hs_app.data.domain.Card
import br.com.gvg_hs_app.data.local.CardDao
import br.com.gvg_hs_app.data.local.asDomainModel
import br.com.gvg_hs_app.data.source.HearthStoneApi
import br.com.gvg_hs_app.data.source.SourceCardContainer
import br.com.gvg_hs_app.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepository(private val cardDao: CardDao) {

    val cards: LiveData<List<Card>> = Transformations.map(
        cardDao.getAllCards()
    ){
        it.asDomainModel()
    }

    suspend fun refreshCards(){
        withContext(Dispatchers.IO){
            val cards = HearthStoneApi.retrofitService.getGvGCards()
            val cardsContainer = SourceCardContainer(cards)
            cardDao.insertAllHeroes(cardsContainer.asLocalModel())
        }
    }

}