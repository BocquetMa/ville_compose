package com.example.ville_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.ville_compose.viewmodel.CityListViewModel
import com.example.ville_compose.viewmodel.SortField

@Composable
fun CityListScreen(viewModel: CityListViewModel) {
    val cities by viewModel.cities.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Liste des villes", style = androidx.compose.ui.text.TextStyle(fontSize = 25.sp))
        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { viewModel.sortCities(SortField.NAME) }) {
                Text("Nom")
            }
            Button(onClick = { viewModel.sortCities(SortField.POPULATION) }) {
                Text("Population")
            }
            Button(onClick = { viewModel.sortCities(SortField.AREA) }) {
                Text("Superficie")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(cities) { city ->
                CityRow(city)
            }
        }
    }
}