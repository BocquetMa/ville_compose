package com.example.ville_compose.repository

import com.example.ville_compose.model.City

class InMemoryCityRepository : CityRepository {

    private val cities = mutableListOf(
        City("Paris", 2161000, 105.4),
        City("Marseille", 870000, 240.6),
        City("Lyon", 522000, 47.9),
        City("Toulouse", 511000, 118.3),
        City("Nice", 342000, 71.9)
    )

    override fun getCities(): List<City> = cities

    override fun addCity(city: City) {
        cities.add(city)
    }

    override fun updateCity(city: City) {
        val index = cities.indexOfFirst { it.name == city.name }
        if (index != -1) {
            cities[index] = cities[index].copy(
                population = city.population,
                areaKm2 = city.areaKm2
            )
        }
    }
}