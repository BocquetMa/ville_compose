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
    private val repository: CityRepository
) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()
    private var currentSortField = SortField.NAME

    init {
        refreshCities()
    }

    fun sortCities(field: SortField) {
        currentSortField = field
        _cities.value = when (field) {
            SortField.NAME -> _cities.value.sortedBy { it.name }
            SortField.POPULATION -> _cities.value.sortedBy { it.population }
            SortField.AREA -> _cities.value.sortedBy { it.areaKm2 }
        }
    }

    fun addCity(city: City) {
        repository.addCity(city)
        refreshCities()
    }

    fun updateCity(city: City) {
        repository.updateCity(city)
        refreshCities()
    }

    private fun refreshCities() {
        _cities.value = repository.getCities()
        sortCities(currentSortField)
    }
}