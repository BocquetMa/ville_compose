package com.example.ville_compose.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.City

@Composable
fun AddCityScreen(onAddCity: (City) -> Unit) {
    var name by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ajouter une ville")
        TextField(value = name, onValueChange = { name = it }, label = { Text("Nom") })
        TextField(value = population, onValueChange = { population = it }, label = { Text("Population") })
        TextField(value = area, onValueChange = { area = it }, label = { Text("Superficie") })
        Button(onClick = {
            val city = City(name, population.toInt(), area.toDouble())
            onAddCity(city)
        }) {
            Text("Ajouter")
        }
    }
}