package com.dsige.cca.data.repository

import com.dsige.cca.data.model.RegisterWork
import com.dsige.cca.data.model.Sync
import com.dsige.cca.data.model.Usuario
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getUsuario(): Flow<Usuario?>
    suspend fun insertUsuario(u: Usuario): Boolean
    suspend fun deleteAll(): Boolean
    suspend fun saveSync(s: Sync): Boolean

    fun getRegisterWorkes(): Flow<List<RegisterWork>>
}