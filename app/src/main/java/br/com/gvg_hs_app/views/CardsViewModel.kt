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
    private val _filter: MutableLiveData<String> = MutableLiveData("")

    val cardList: LiveData<List<Card>>
        get() {
            return if(_filter.value == ""){
                _cardList
            }
            else{
                val cards: List<Card> = _cardList.value?.filter {
                    it.name.contains(_filter.value ?: "")
                } ?: listOf()
                MutableLiveData(cards)
            }
        }

    val filter: LiveData<String>
        get() = _filter

    init{
        getCards()
    }

    fun updateFilter(word: String){
        _filter.value = word
    }

    private fun getCards(){
        viewModelScope.launch {
            try{
                val listResult = HearthStoneApi.retrofitService.getGvGCards()
                _cardList.value = listResult
            }catch (e: Exception){
                _cardList.value = listOf()
                Log.i("GetCards", "${e.message}")
            }
        }
    }


}