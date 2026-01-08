package com.example.ville_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.ui.theme.*
import com.example.ville_compose.viewmodel.CityListViewModel
import com.example.ville_compose.viewmodel.SortField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(viewModel: CityListViewModel, onCityClick: (CityEntity) -> Unit) {
    val cities by viewModel.cities.collectAsState()
    var showSortMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        // Header
        Surface(
            color = AppColors.Surface,
            shadowElevation = 2.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppSpacing.Medium)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Villes",
                        style = AppTypography.Heading1
                    )

                    IconButton(onClick = { showSortMenu = true }) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = "Filtrer",
                            tint = AppColors.Primary
                        )
                    }

                    DropdownMenu(
                        expanded = showSortMenu,
                        onDismissRequest = { showSortMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Trier par nom") },
                            onClick = {
                                viewModel.sortCities(SortField.NAME)
                                showSortMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Trier par population") },
                            onClick = {
                                viewModel.sortCities(SortField.POPULATION)
                                showSortMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Trier par superficie") },
                            onClick = {
                                viewModel.sortCities(SortField.AREA)
                                showSortMenu = false
                            }
                        )
                    }
                }

                Text(
                    text = "${cities.size} ville(s) trouvée(s)",
                    style = AppTypography.BodySecondary
                )
            }
        }

        // Liste des villes
        if (cities.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aucune ville ajoutée",
                    style = AppTypography.BodySecondary,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(AppSpacing.Medium),
                verticalArrangement = Arrangement.spacedBy(AppSpacing.Medium)
            ) {
                items(cities) { city ->
                    CityCard(city = city, onClick = { onCityClick(city) })
                }
            }
        }
    }
}
