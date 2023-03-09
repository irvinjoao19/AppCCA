package com.dsige.cca.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dsige.cca.data.dao.*
import com.dsige.cca.data.model.*

@Database(
    entities = [
        Usuario::class,
        Actividad::class,
        SubActividad::class,
        Parametro::class,
        RegisterWork::class
    ],
    version = 3, // version 1 en play store
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun actividadDao(): ActividadDao
    abstract fun subActividadDao(): SubActividadDao
    abstract fun parametroDao(): ParametroDao
    abstract fun registerWorkDao(): RegisterWorkDao
}