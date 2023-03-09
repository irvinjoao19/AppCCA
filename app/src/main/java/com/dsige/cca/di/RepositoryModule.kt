package com.dsige.cca.di

import com.dsige.cca.data.implementacion.LocalRepositoryImpl
import com.dsige.cca.data.implementacion.RestRepositoryImpl
import com.dsige.cca.data.AppDataBase
import com.dsige.cca.data.network.IApiClient
import com.dsige.cca.data.repository.LocalRepository
import com.dsige.cca.data.repository.RestRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideLocalRepository(dataBase: AppDataBase): LocalRepository = LocalRepositoryImpl(dataBase)

    @Provides
    fun provideRestRepository(iApiClient: IApiClient): RestRepository = RestRepositoryImpl(iApiClient)

}