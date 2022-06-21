package com.moragar.pokewiki.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.moragar.pokewiki.R
import com.moragar.pokewiki.databinding.ContainerMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Container : AppCompatActivity() {
    private lateinit var binding: ContainerMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContainerMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}