package com.dsige.cca.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsige.cca.data.model.Actividad
import kotlinx.coroutines.flow.Flow

@Dao
interface ActividadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActividadTask(f: List<Actividad>)

    @Query("DELETE FROM Actividad")
    suspend fun deleteAll()

    @Query("SELECT * FROM Actividad")
    fun getActividades(): Flow<List<Actividad>>
}