package com.dsige.cca.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dsige.cca.data.model.Actividad
import com.dsige.cca.data.model.RegisterWork
import kotlinx.coroutines.flow.Flow

@Dao
interface RegisterWorkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegisterWorkTask(r: RegisterWork)

    @Query("DELETE FROM RegisterWork")
    suspend fun deleteAll()

    @Query("SELECT * FROM RegisterWork WHERE active=:a")
    fun getRegisterWorkes(a: Int): Flow<List<RegisterWork>>
}