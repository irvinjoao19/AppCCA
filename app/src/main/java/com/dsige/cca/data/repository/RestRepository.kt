package com.dsige.cca.data.repository

import com.dsige.cca.data.model.*
import kotlinx.coroutines.flow.Flow

interface RestRepository {

    suspend fun getLogin(model: MutableMap<String, Any>): Flow<Usuario>

    suspend fun getSync(): Flow<Sync>
}