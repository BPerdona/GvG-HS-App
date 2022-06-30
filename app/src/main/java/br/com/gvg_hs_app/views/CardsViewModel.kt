package br.com.gvg_hs_app.views

import android.util.Log
import androidx.lifecycle.*
import br.com.gvg_hs_app.data.domain.Card
import br.com.gvg_hs_app.data.source.HearthStoneApi
import br.com.gvg_hs_app.data.repository.CardRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class CardsViewModel(private val repository: CardRepository): ViewModel() {

    init{
        if(repository.cards.value.isNullOrEmpty()){
            refreshDataFromRepository()
        }
    }

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

    fun updateFilter(word: String){
        _filter.value = word
    }

    private fun refreshDataFromRepository(){
        viewModelScope.launch {
            try{
                repository.refreshCards()
            }catch (ioe: IOException){
                Log.e("GetCards", "${ioe.message}")
            }
        }
    }
}

class CardVMFactory(private val repository: CardRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CardsViewModel::class.java))
            return CardsViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}