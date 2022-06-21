package com.moragar.pokewiki.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.moragar.pokewiki.R
import com.moragar.pokewiki.databinding.FragmentPokemonDetailBinding
import com.moragar.pokewiki.model.data.AbilitySlot
import com.moragar.pokewiki.model.data.PokemonDetail
import com.moragar.pokewiki.viewModel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var adapter: AbilityAdapter
    private var pId:Int =0
    private val abilityList: MutableList<AbilitySlot> = mutableListOf()
    private val pokemonsViewModel: PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pId = it.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonDetailBinding.bind(view)
        adapter = AbilityAdapter(abilityList)
        binding.rvAbility.adapter = adapter
        initViewModels()
    }

    private fun initViewModels() {
        pokemonsViewModel.getPokemonDetail(pId)
        pokemonsViewModel.detailLiveData.observe(viewLifecycleOwner, Observer {
            if (it.name.isNotEmpty()){
                Glide.with(requireContext()).load(it.sprites.front_default).into(binding.image)
                binding.number.text = "#${it.id}"
                binding.name.text = it.name
                binding.type.text = "Type: ${it.types[0].type.name}"
                abilityList.addAll(it.abilities)
                adapter.notifyDataSetChanged()
            }
        })
    }
}