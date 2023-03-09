package com.dsige.cca.data.network

import com.dsige.cca.data.model.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface IApiClient {

    @Headers("Cache-Control: no-cache")
    @POST("Login")
    suspend fun getLogin(@Body body: RequestBody): Usuario

    @Headers("Cache-Control: no-cache")
    @POST("Sync")
    suspend fun getSync(): Sync

//    @Headers("Cache-Control: no-cache")
//    @POST("SaveFiles")
//    suspend fun saveFiles(@Body body: RequestBody): Mensaje
//
//    @Headers("Cache-Control: no-cache")
//    @POST("SaveWork")
//    suspend fun saveWork(@Body body: RequestBody): Mensaje


}