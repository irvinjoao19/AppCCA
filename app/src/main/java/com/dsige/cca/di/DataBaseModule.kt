package com.dsige.cca.di

import android.app.Application
import androidx.room.Room
import com.dsige.cca.data.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {

    @Volatile
    var INSTANCE: AppDataBase? = null
    val DB_NAME = "cca_db"

    @Provides
    internal fun provideRoomDatabase(application: Application): AppDataBase =
        INSTANCE ?: synchronized(this) {
            val dataBase by lazy {
                Room.databaseBuilder(
                    application.applicationContext,
                    AppDataBase::class.java, DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            INSTANCE = dataBase
            dataBase
        }
}