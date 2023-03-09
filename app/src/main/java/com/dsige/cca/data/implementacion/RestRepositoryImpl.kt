package com.dsige.cca.data.implementacion

import com.google.gson.Gson
import com.dsige.cca.data.model.*
import com.dsige.cca.data.network.IApiClient
import com.dsige.cca.data.repository.RestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject

class RestRepositoryImpl @Inject constructor(private val iApiClient: IApiClient) : RestRepository {

    override suspend fun getLogin(model: MutableMap<String, Any>): Flow<Usuario> = flow {
        val json = Gson().toJson(model)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val result = iApiClient.getLogin(body)
        emit(result)
    }

    override suspend fun getSync(): Flow<Sync> = flow {
        val result = iApiClient.getSync()
        emit(result)
    }
}