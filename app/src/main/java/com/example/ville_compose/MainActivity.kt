package com.example.ville_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.ville_compose.repository.InMemoryCityRepository
import com.example.ville_compose.ui.CityListScreen
import com.example.ville_compose.viewmodel.CityListViewModel
import com.example.ville_compose.viewmodel.CityListViewModelFactory
class MainActivity : ComponentActivity() {

    private val vm: CityListViewModel by viewModels{
        CityListViewModelFactory(InMemoryCityRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContent{
            CityListScreen(vm)
        }
    }
}