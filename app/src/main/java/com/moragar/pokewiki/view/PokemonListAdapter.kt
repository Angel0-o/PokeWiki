package com.moragar.pokewiki.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.moragar.pokewiki.R
import com.moragar.pokewiki.databinding.ItemPokemonBinding
import com.moragar.pokewiki.model.data.Pokemon

class PokemonListAdapter(val pokemonList:List<Pokemon> = emptyList()):
    Adapter<PokemonListAdapter.ViewHolder>() {
    lateinit var pListener:OnPokemonListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_pokemon,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindPokemon(pokemonList[position],position,pListener)
    }

    override fun getItemCount(): Int = pokemonList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ItemPokemonBinding

        fun bindPokemon(pokemon: Pokemon,position: Int,listener: OnPokemonListener){
            binding = ItemPokemonBinding.bind(itemView)
            binding.pokemonName.text = pokemon.name
            Glide.with(itemView.context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position+1}.png")
                .centerCrop()
                .into(binding.pokemonImage)
            itemView.setOnClickListener { listener.OnPokemonClickListener(position+1) }
        }
    }

    interface OnPokemonListener{
        fun OnPokemonClickListener(position: Int)
    }

    fun setPokemonListener(listener: OnPokemonListener){pListener=listener}
}