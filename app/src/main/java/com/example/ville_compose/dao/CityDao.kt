package com.example.ville_compose.dao

import androidx.room.*
import com.example.ville_compose.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao{
    @Query("select * from cities order by name asc")
    fun observeAll(): Flow<List<CityEntity>>

    @Insert
    suspend fun insert(city: CityEntity): Long

    @Update
    suspend fun update(city: CityEntity)

    @Query("delete from cities where id = :id")
    suspend fun deleteById(id: Long)
}