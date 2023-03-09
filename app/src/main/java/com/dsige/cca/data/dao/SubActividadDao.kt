package com.dsige.cca.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsige.cca.data.model.SubActividad
import kotlinx.coroutines.flow.Flow

@Dao
interface SubActividadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubActividadTask(f: List<SubActividad>)

    @Query("DELETE FROM SubActividad")
    suspend fun deleteAll()

    @Query("SELECT * FROM SubActividad WHERE activityId=:id")
    fun getSubActividadById(id:Int): Flow<List<SubActividad>>
}