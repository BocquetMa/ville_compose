package com.example.ville_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.ville_compose.repository.InMemoryCityRepository
import com.example.ville_compose.ui.AppNavigation
import com.example.ville_compose.viewmodel.CityListViewModel
import com.example.ville_compose.viewmodel.CityListViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cityRepository = InMemoryCityRepository()
        val viewModelFactory = CityListViewModelFactory(cityRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CityListViewModel::class.java)
        setContent {
            AppNavigation(viewModel = viewModel)
        }
    }
}