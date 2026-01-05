package com.example.ville_compose.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.City

@Composable
fun CityRow(city: City) {
    Column(
        modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Black)
        .padding(8.dp)
    ){
        BasicText(text = city.name)
        BasicText(text = "Habitants : ${city.population}")
        BasicText(text = "Superficie : ${city.areaKm2} km2")
    }
}