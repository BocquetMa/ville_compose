package com.example.ville_compose.repository

import com.example.ville_compose.model.City

class InMemoryCityRepository : CityRepository{

    override fun getCities(): List<City> =
        listOf(
            City("Paris", 2161000, 105.4),
            City("Marseille", 870000, 240.6),
            City("Lyon", 522000, 47.9),
            City("Toulouse", 511000, 118.3),
            City("Nice", 342000, 71.9)
        )
}