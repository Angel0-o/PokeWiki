package com.moragar.pokewiki.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moragar.pokewiki.model.data.PokemonDetail
import com.moragar.pokewiki.model.data.PokemonResponse
import com.moragar.pokewiki.model.repositories.PokemonListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonListRepository
):ViewModel() {
    val pokemonsLiveData = MutableLiveData<PokemonResponse>()
    val detailLiveData = MutableLiveData<PokemonDetail>()

    fun getPokemonList(offset:Int =0,limit:Int =20){
        viewModelScope.launch {
            pokemonsLiveData.postValue(repository.getPokemonList(offset, limit))
        }
    }

    fun getPokemonDetail(id:Int =0){
        viewModelScope.launch {
            detailLiveData.postValue(repository.getPokemonDetail(id))
        }
    }
}