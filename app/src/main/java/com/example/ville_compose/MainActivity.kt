package com.example.ville_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.ville_compose.dao.CityDatabase
import com.example.ville_compose.repository.CityRepository
import com.example.ville_compose.ui.AppNavigation
import com.example.ville_compose.viewmodel.CityListViewModel
import com.example.ville_compose.viewmodel.CityListViewModelFactory
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            CityDatabase::class.java,
            "city_database"
        ).build()

        val repository = CityRepository(db.cityDao())
        val factory = CityListViewModelFactory(repository)

        setContent {
            val viewModel: CityListViewModel = viewModel(factory = factory)
            AppNavigation(viewModel = viewModel)
        }
    }
}