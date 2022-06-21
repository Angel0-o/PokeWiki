package com.moragar.pokewiki.model.network

import com.moragar.pokewiki.model.data.PokemonDetail
import com.moragar.pokewiki.model.data.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset:Int,
        @Query("limit") limit:Int,
    ):Response<PokemonResponse>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") id:Int
    ):Response<PokemonDetail>
}