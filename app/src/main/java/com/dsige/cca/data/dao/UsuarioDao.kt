package com.dsige.cca.data.dao

import androidx.room.*
import com.dsige.cca.data.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuarioTask(c: Usuario)

    @Query("DELETE FROM Usuario")
    suspend fun deleteAll()

    @Query("SELECT * FROM Usuario")
    fun getUsuario(): Flow<Usuario?>
}