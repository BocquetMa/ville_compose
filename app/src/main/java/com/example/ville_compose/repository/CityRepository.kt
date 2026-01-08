package com.example.ville_compose.repository

import com.example.ville_compose.model.City

interface CityRepository {
    fun getCities() : List<City>
    fun addCity(city: City)
    fun updateCity(city: City)
}