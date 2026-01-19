package com.example.ville_compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.ui.theme.AppColors
import com.example.ville_compose.ui.theme.AppSpacing
import com.example.ville_compose.ui.theme.AppTypography

@Composable
fun CityCard(
    city: CityEntity, 
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = AppColors.Surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(AppSpacing.Medium)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
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
            
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favori",
                    tint = if (city.isFavorite) AppColors.Primary else AppColors.TextSecondary
                )
            }
        }
    }
}
