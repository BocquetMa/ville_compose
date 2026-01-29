package com.example.ville_compose.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ville_compose.model.CityEntity

@Database(entities = [CityEntity::class], version = 2)
abstract class CityDatabase : RoomDatabase(){
    abstract fun cityDao(): CityDao
}