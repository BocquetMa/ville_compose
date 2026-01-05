package com.example.ville_compose.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ville_compose.model.City
import com.example.ville_compose.repository.CityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CityListViewModel (
    repository: CityRepository
) : ViewModel() {

    private val _cities = MutableStateFlow<List<City>>(emptyList())
    val cities: StateFlow<List<City>> = _cities.asStateFlow()

    init {
        _cities.value = repository.getCities()
    }

}