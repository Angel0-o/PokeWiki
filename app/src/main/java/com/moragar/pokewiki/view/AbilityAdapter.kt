package com.moragar.pokewiki.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moragar.pokewiki.R
import com.moragar.pokewiki.databinding.ItemAbilityBinding
import com.moragar.pokewiki.model.data.AbilitySlot

class AbilityAdapter(val abilityList: List<AbilitySlot> = emptyList()): RecyclerView.Adapter<AbilityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_ability,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindAbility(abilityList[position])
    }

    override fun getItemCount(): Int =abilityList.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var binding: ItemAbilityBinding

        fun bindAbility(ability: AbilitySlot){
            binding = ItemAbilityBinding.bind(itemView)
            binding.name.text = "Ability: ${ability.ability.name}"
            binding.hidden.text = "Hidden: ${ability.is_hidden}"
        }
    }
}