package com.example.ville_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.viewmodel.CityListViewModel
@Composable
fun CityListScreen(viewModel: CityListViewModel) {
    val cities by viewModel.cities.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        BasicText(text = "Liste des villes")
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(cities) { city ->
                CityRow(city)
            }
        }
    }
}