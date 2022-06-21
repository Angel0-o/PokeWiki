package com.moragar.pokewiki.model.repositories

import com.moragar.pokewiki.model.data.PokemonDetail
import com.moragar.pokewiki.model.data.PokemonResponse
import com.moragar.pokewiki.model.network.PokemonClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PokemonListRepository @Inject constructor() {

    suspend fun getPokemonList(offset:Int,limit:Int):PokemonResponse{
        return withContext(Dispatchers.IO){
            val response: Response<PokemonResponse> = PokemonClient.instance.getPokemonList(offset, limit)
            response.body()?: PokemonResponse()
        }
    }

    suspend fun getPokemonDetail(id:Int):PokemonDetail{
        return withContext(Dispatchers.IO){
            val response: Response<PokemonDetail> = PokemonClient.instance.getPokemonDetail(id)
            response.body()?: PokemonDetail()
        }
    }
}