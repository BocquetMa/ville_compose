package com.example.ville_compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.ui.theme.*

@Composable
fun CityRow(city: CityEntity, onCityClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCityClick() },
        shape = AppShapes.Medium,
        colors = CardDefaults.cardColors(containerColor = AppColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.Medium)
        ) {
            Text(
                text = city.name,
                style = AppTypography.Heading2
            )
            Spacer(modifier = Modifier.height(AppSpacing.Small))
            Text(
                text = "Habitants : ${city.population}",
                style = AppTypography.Body
            )
            Text(
                text = "Superficie : ${city.areaKm2} km²",
                style = AppTypography.BodySecondary
            )
        }
    }
}