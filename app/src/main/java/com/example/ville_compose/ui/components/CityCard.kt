package com.example.ville_compose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.ui.theme.*

@Composable
fun CityCard(city: CityEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = AppShapes.Medium,
        colors = CardDefaults.cardColors(containerColor = AppColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.Medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icône
            Surface(
                shape = AppShapes.Small,
                color = AppColors.Primary.copy(alpha = 0.1f),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = AppColors.Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(AppSpacing.Medium))

            // Contenu
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = city.name,
                    style = AppTypography.Heading2,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(AppSpacing.ExtraSmall))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(AppSpacing.Medium)
                ) {
                    InfoChip(
                        label = "Population",
                        value = formatNumber(city.population)
                    )

                    InfoChip(
                        label = "Superficie",
                        value = "${city.areaKm2} km²"
                    )
                }
            }

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Voir détails",
                tint = AppColors.TextSecondary
            )
        }
    }
}

@Composable
fun InfoChip(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = AppTypography.Caption
        )
        Text(
            text = value,
            style = AppTypography.BodySecondary.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Medium)
        )
    }
}

fun formatNumber(number: Int): String {
    return when {
        number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
        number >= 1_000 -> String.format("%.1fK", number / 1_000.0)
        else -> number.toString()
    }
}