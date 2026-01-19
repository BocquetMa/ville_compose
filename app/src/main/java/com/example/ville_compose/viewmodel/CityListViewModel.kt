package com.example.ville_compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.repository.CityRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class SortField { NAME, POPULATION, AREA }

class CityListViewModel(private val repo: CityRepository) : ViewModel() {

    private val _sortField = MutableStateFlow(SortField.NAME)

    val cities: StateFlow<List<CityEntity>> = repo.observeAll()
        .combine(_sortField) { cities, sortField ->
            val sortedList = when (sortField) {
                SortField.NAME -> cities.sortedBy { it.name }
                SortField.POPULATION -> cities.sortedByDescending { it.population }
                SortField.AREA -> cities.sortedByDescending { it.areaKm2 }
            }
            // Sort by favorite first (true > false)
            sortedList.sortedByDescending { it.isFavorite }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun sortCities(field: SortField) {
        _sortField.value = field
    }

    fun toggleFavorite(city: CityEntity) = viewModelScope.launch {
        repo.toggleFavorite(city)
    }

    fun addCity(name: String, population: Int, areaKm2: Double) = viewModelScope.launch {
        if (name.isNotBlank()) {
            repo.add(name.trim(), population, areaKm2)
        }
    }

    fun updateCity(city: CityEntity) = viewModelScope.launch {
        repo.update(city)
    }

    fun deleteCity(id: Long) = viewModelScope.launch {
        repo.delete(id)
    }
}