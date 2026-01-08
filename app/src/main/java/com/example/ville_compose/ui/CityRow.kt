package com.example.ville_compose.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.City

@Composable
fun CityRow(city: City, onCityClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .clickable { onCityClick() }
            .padding(8.dp)
    ){
        Text(text = city.name)
        Text(text = "Habitants : ${city.population}")
        Text(text = "Superficie : ${city.areaKm2} km2")
    }
}