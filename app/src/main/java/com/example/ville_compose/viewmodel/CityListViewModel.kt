package com.example.ville_compose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ville_compose.model.City
import com.example.ville_compose.repository.CityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class SortField {
    NAME,
    POPULATION,
    AREA
}

class CityListViewModel (
    repository: CityRepository
) : ViewModel() {

    private val originalCities = repository.getCities()
    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    init {
        sortCities(SortField.NAME)
    }

    fun sortCities(field: SortField) {
        _cities.value = when (field) {
            SortField.NAME -> originalCities.sortedBy { it.name }
            SortField.POPULATION -> originalCities.sortedBy { it.population }
            SortField.AREA -> originalCities.sortedBy { it.areaKm2 }
        }
    }
}