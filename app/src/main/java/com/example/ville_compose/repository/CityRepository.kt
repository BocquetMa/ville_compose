package com.example.ville_compose.repository

import com.example.ville_compose.model.CityEntity
import com.example.ville_compose.dao.CityDao
import kotlinx.coroutines.flow.Flow

class CityRepository(private val dao: CityDao){

    fun observeAll(): Flow<List<CityEntity>> = dao.observeAll()

    suspend fun add(name: String, population: Int, areaKm2: Double) =
        dao.insert(CityEntity(name = name, population = population, areaKm2 = areaKm2))

    suspend fun update(city: CityEntity) = dao.update(city)

    suspend fun delete(id: Long) = dao.deleteById(id)
}