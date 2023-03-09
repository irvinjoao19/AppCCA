package com.dsige.cca.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsige.cca.data.model.Parametro
import kotlinx.coroutines.flow.Flow

@Dao
interface ParametroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParametroTask(f: List<Parametro>)

    @Query("DELETE FROM Parametro")
    suspend fun deleteAll()

    @Query("SELECT * FROM Parametro")
    fun getParametros(): Flow<List<Parametro>>
}