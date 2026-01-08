package com.example.ville_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ville_compose.ui.theme.*
import com.example.ville_compose.viewmodel.CityListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCityScreen(viewModel: CityListViewModel, onNavigateBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajouter une ville", style = AppTypography.Heading2) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour",
                            tint = AppColors.Primary
                        )
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
            if (showError) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    ),
                    shape = AppShapes.Small
                ) {
                    Text(
                        text = "Veuillez remplir tous les champs correctement",
                        style = AppTypography.BodySecondary.copy(
                            color = MaterialTheme.colorScheme.onErrorContainer
                        ),
                        modifier = Modifier.padding(AppSpacing.Medium)
                    )
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    showError = false
                },
                label = { Text("Nom de la ville") },
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Small,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.Primary,
                    unfocusedBorderColor = AppColors.Border
                )
            )

            OutlinedTextField(
                value = population,
                onValueChange = {
                    population = it.filter { char -> char.isDigit() }
                    showError = false
                },
                label = { Text("Population") },
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Small,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.Primary,
                    unfocusedBorderColor = AppColors.Border
                )
            )

            OutlinedTextField(
                value = area,
                onValueChange = {
                    area = it
                    showError = false
                },
                label = { Text("Superficie (km²)") },
                modifier = Modifier.fillMaxWidth(),
                shape = AppShapes.Small,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.Primary,
                    unfocusedBorderColor = AppColors.Border
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val populationInt = population.toIntOrNull() ?: 0
                    val areaDouble = area.toDoubleOrNull() ?: 0.0

                    if (name.isNotBlank() && populationInt > 0 && areaDouble > 0) {
                        viewModel.addCity(name, populationInt, areaDouble)
                        onNavigateBack()
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
                Text("Ajouter la ville", style = AppTypography.Button)
            }
        }
    }
}