package com.example.ville_compose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    val name: String,
    val population: Int,
    val areaKm2: Double

)