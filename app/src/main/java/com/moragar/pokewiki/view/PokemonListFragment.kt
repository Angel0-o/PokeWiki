package com.moragar.pokewiki.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moragar.pokewiki.R
import com.moragar.pokewiki.databinding.FragmentPokemonListBinding
import com.moragar.pokewiki.model.data.Pokemon
import com.moragar.pokewiki.viewModel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment(),PokemonListAdapter.OnPokemonListener {
    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var adapter: PokemonListAdapter
    private val pokemonList: MutableList<Pokemon> = mutableListOf()
    private val pokemonsViewModel:PokemonListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonListBinding.bind(view)
        setHasOptionsMenu(true)
        adapter = PokemonListAdapter(pokemonList)
        adapter.setPokemonListener(this)
        binding.rvPokemon.adapter = adapter
        initViewModels()
        binding.srPokemon.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            pokemonsViewModel.getPokemonList(pokemonList.size,20)
            binding.srPokemon.isRefreshing = false
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.switch_views_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.list_view->{
                binding.rvPokemon.layoutManager = LinearLayoutManager(requireContext())
                binding.rvPokemon.adapter = adapter
            }
            R.id.grid_view->{
                binding.rvPokemon.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rvPokemon.adapter = adapter
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViewModels() {
        pokemonsViewModel.getPokemonList()
        pokemonsViewModel.pokemonsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.results.isNotEmpty()){
                pokemonList.addAll(it.results)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun OnPokemonClickListener(position:Int) {
        val args:Bundle = Bundle().apply {
            putInt("id",position)
        }
        findNavController().navigate(R.id.action_pokemonListFragment_to_pokemonDetailFragment,args)
    }
}