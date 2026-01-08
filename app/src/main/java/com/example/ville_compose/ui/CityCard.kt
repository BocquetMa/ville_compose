package com.example.ville_compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.ui.theme.AppColors
import com.example.ville_compose.ui.theme.AppSpacing
import com.example.ville_compose.ui.theme.AppTypography

@Composable
fun CityCard(city: CityEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.Surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(AppSpacing.Medium)
        ) {
            Text(
                text = city.name,
                style = AppTypography.Heading2,
                color = AppColors.TextPrimary
            )
            Text(
                text = "Population : ${city.population}",
                style = AppTypography.Body,
                color = AppColors.TextSecondary
            )
            Text(
                text = "Superficie : ${city.areaKm2} km²",
                style = AppTypography.Body,
                color = AppColors.TextSecondary
            )
        }
    }
}
