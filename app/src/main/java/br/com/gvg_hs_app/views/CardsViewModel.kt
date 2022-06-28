package br.com.gvg_hs_app.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gvg_hs_app.data.Card
import br.com.gvg_hs_app.network.HearthStoneApi
import kotlinx.coroutines.launch

class CardsViewModel: ViewModel() {

    private val _cardList = MutableLiveData<List<Card>>()
    val cardList: LiveData<List<Card>>
        get() = _cardList

    init{
        getCards()
    }

    private fun getCards(){
        viewModelScope.launch {
            try{
                Log.e("GetCards", "Tentou recuperar")
                val listResult = HearthStoneApi.retrofitService.getGvGCards()
                _cardList.value = listResult
                Log.e("GetCards", "Recuperou: ${listResult.toString()}")
            }catch (e: Exception){
                _cardList.value = null
                Log.e("GetCards", "${e.message}")
            }
        }
    }
}