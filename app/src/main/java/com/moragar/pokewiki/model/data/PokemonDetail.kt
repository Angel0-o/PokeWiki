package com.moragar.pokewiki.model.data

data class PokemonDetail(
    val abilities:List<AbilitySlot> = emptyList(),
    val id:Int =0,
    val name:String ="",
    val sprites:Sprites = Sprites(),
    val types:List<TypeSlot> = emptyList()
)
