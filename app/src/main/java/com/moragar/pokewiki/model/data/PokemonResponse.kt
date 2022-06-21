package com.moragar.pokewiki.model.data


data class PokemonResponse(
    val count:Int =0,
    val next:String ="",
    val previous:String ="",
    val results:List<Pokemon> = emptyList(),
)
