package com.dsige.cca.di

import com.dsige.cca.usesCase.checkSessionUsesCase.CheckSessionUsesCase
import com.dsige.cca.usesCase.loginUsesCase.LoginLocalUsesCase
import com.dsige.cca.usesCase.loginUsesCase.LoginRestUsesCase
import com.dsige.cca.usesCase.syncUsesCase.SyncLocalUsesCase
import com.dsige.cca.usesCase.syncUsesCase.SyncRestUsesCase
import com.dsige.cca.usesCase.workUsesCase.WorkLocalUsesCase
import com.dsige.cca.viewModel.CheckSessionViewModel
import com.dsige.cca.viewModel.LoginViewModel
import com.dsige.cca.viewModel.WorkViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class ViewModelsModule {

    @Provides
    fun provideCheckSessionViewModel(checkSessionUsesCase: CheckSessionUsesCase) =
        CheckSessionViewModel(checkSessionUsesCase)

    @Provides
    fun provideLoginViewModel(
        loginLocalUsesCase: LoginLocalUsesCase,
        loginRestUsesCase: LoginRestUsesCase,
        syncLocalUsesCase: SyncLocalUsesCase,
        syncRestUsesCase: SyncRestUsesCase,
    ) = LoginViewModel(
        loginLocalUsesCase,
        loginRestUsesCase,
        syncLocalUsesCase,
        syncRestUsesCase,
    )

    @Provides
    fun provideWorkViewModel(workLocalUsesCase: WorkLocalUsesCase) =
        WorkViewModel(workLocalUsesCase)
}