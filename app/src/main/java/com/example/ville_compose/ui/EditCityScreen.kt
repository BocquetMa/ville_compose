package com.example.ville_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCityScreen(city: City, onUpdateCity: (City) -> Unit, onNavigateBack: () -> Unit) {
    var population by remember { mutableStateOf(city.population.toString()) }
    var area by remember { mutableStateOf(city.areaKm2.toString()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Modifier ${city.name}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = population,
                onValueChange = { population = it },
                label = { Text("Population") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = area,
                onValueChange = { area = it },
                label = { Text("Superficie (km²)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val updatedCity = city.copy(
                        population = population.toIntOrNull() ?: city.population,
                        areaKm2 = area.toDoubleOrNull() ?: city.areaKm2
                    )
                    onUpdateCity(updatedCity)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Sauvegarder les modifications")
            }
        }
    }
}
