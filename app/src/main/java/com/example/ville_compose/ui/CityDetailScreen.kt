package com.example.ville_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityDetailScreen(city: CityEntity, onEditClick: () -> Unit, onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = city.name) },
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
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = city.name, style = MaterialTheme.typography.headlineLarge)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Text(text = "Population : ${city.population}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Superficie : ${city.areaKm2} km²", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Button(onClick = onEditClick) {
                Text("Modifier")
            }
        }
    }
}
