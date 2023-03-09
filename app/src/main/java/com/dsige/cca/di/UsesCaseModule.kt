package com.dsige.cca.di

import com.dsige.cca.data.repository.LocalRepository
import com.dsige.cca.data.repository.RestRepository
import com.dsige.cca.usesCase.loginUsesCase.LoginLocalUsesCase
import com.dsige.cca.usesCase.loginUsesCase.LoginRestUsesCase
import com.dsige.cca.usesCase.syncUsesCase.SyncLocalUsesCase
import com.dsige.cca.usesCase.syncUsesCase.SyncRestUsesCase
import com.dsige.cca.usesCase.workUsesCase.WorkLocalUsesCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UsesCaseModule {

    @Provides
    fun provideLoginLocalUsesCase(localRepository: LocalRepository) =
        LoginLocalUsesCase(localRepository)

    @Provides
    fun provideLoginRestUsesCase(restRepository: RestRepository) =
        LoginRestUsesCase(restRepository)

    @Provides
    fun provideSyncLocalUsesCase(localRepository: LocalRepository) =
        SyncLocalUsesCase(localRepository)

    @Provides
    fun provideSyncRestUsesCase(restRepository: RestRepository) =
        SyncRestUsesCase(restRepository)

    @Provides
    fun provideWorkLocalUsesCase(localRepository: LocalRepository) =
        WorkLocalUsesCase(localRepository)
}