package com.example.ville_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCityScreen(
    city: CityEntity,
    onUpdateCity: (CityEntity) -> Unit,
    onDeleteCity: ((Long) -> Unit)? = null,
    onNavigateBack: () -> Unit
) {
    var population by remember { mutableStateOf(city.population.toString()) }
    var area by remember { mutableStateOf(city.areaKm2.toString()) }
    var showError by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Modifier ${city.name}",
                        style = AppTypography.Heading2
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour",
                            tint = AppColors.Primary
                        )
                    }
                },
                actions = {
                    if (onDeleteCity != null) {
                        IconButton(onClick = { showDeleteDialog = true }) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Supprimer",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.Surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.Background)
                .padding(innerPadding)
                .padding(AppSpacing.Medium),
            verticalArrangement = Arrangement.spacedBy(AppSpacing.Medium)
        ) {
            // Carte d'information
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Medium,
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.Primary.copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(AppSpacing.Medium)
                ) {
                    Text(
                        text = city.name,
                        style = AppTypography.Heading1.copy(color = AppColors.Primary)
                    )
                    Spacer(modifier = Modifier.height(AppSpacing.ExtraSmall))
                    Text(
                        text = "Modification des informations",
                        style = AppTypography.BodySecondary
                    )
                }
            }

            if (showError) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    shape = AppShapes.Small
                ) {
                    Text(
                        text = "Veuillez entrer des valeurs valides",
                        style = AppTypography.BodySecondary.copy(
                            color = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        modifier = Modifier.padding(AppSpacing.Medium)
                    )
                }
            }

            // Champ Population
            OutlinedTextField(
                value = population,
                onValueChange = {
                    population = it.filter { char -> char.isDigit() }
                    showError = false
                },
                label = { Text("Population") },
                placeholder = { Text("Ex: 500000") },
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Small,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.Primary,
                    unfocusedBorderColor = AppColors.Border,
                    focusedLabelColor = AppColors.Primary
                ),
                singleLine = true
            )

            // Champ Superficie
            OutlinedTextField(
                value = area,
                onValueChange = {
                    area = it
                    showError = false
                },
                label = { Text("Superficie (km²)") },
                placeholder = { Text("Ex: 105.4") },
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Small,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.Primary,
                    unfocusedBorderColor = AppColors.Border,
                    focusedLabelColor = AppColors.Primary
                ),
                singleLine = true
            )

            // Informations actuelles
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Small,
                colors = CardDefaults.cardColors(
                    containerColor = AppColors.Surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(
                    modifier = Modifier.padding(AppSpacing.Medium)
                ) {
                    Text(
                        text = "Valeurs actuelles",
                        style = AppTypography.Caption
                    )
                    Spacer(modifier = Modifier.height(AppSpacing.Small))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Population",
                                style = AppTypography.BodySecondary
                            )
                            Text(
                                text = city.population.toString(),
                                style = AppTypography.Body
                            )
                        }
                        Column {
                            Text(
                                text = "Superficie",
                                style = AppTypography.BodySecondary
                            )
                            Text(
                                text = "${city.areaKm2} km²",
                                style = AppTypography.Body
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bouton Sauvegarder
            Button(
                onClick = {
                    val populationInt = population.toIntOrNull()
                    val areaDouble = area.toDoubleOrNull()

                    if (populationInt != null && populationInt > 0 &&
                        areaDouble != null && areaDouble > 0) {
                        val updatedCity = city.copy(
                            population = populationInt,
                            areaKm2 = areaDouble
                        )
                        onUpdateCity(updatedCity)
                    } else {
                        showError = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = AppShapes.Medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.Primary
                )
            ) {
                Text(
                    text = "Sauvegarder les modifications",
                    style = AppTypography.Button
                )
            }
        }
    }

    // Dialogue de confirmation de suppression
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = {
                Text(
                    text = "Supprimer ${city.name} ?",
                    style = AppTypography.Heading2
                )
            },
            text = {
                Text(
                    text = "Cette action est irréversible. Voulez-vous vraiment supprimer cette ville ?",
                    style = AppTypography.Body
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteCity?.invoke(city.id)
                        showDeleteDialog = false
                        onNavigateBack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Supprimer")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Annuler", color = AppColors.Primary)
                }
            },
            shape = AppShapes.Medium
        )
    }
}