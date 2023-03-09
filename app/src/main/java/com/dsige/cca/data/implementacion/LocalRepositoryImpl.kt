package com.dsige.cca.data.implementacion

import com.dsige.cca.data.AppDataBase
import com.dsige.cca.data.model.*
import com.dsige.cca.data.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dataBase: AppDataBase) : LocalRepository {

    override fun getUsuario(): Flow<Usuario?> = dataBase.usuarioDao().getUsuario()

    override suspend fun insertUsuario(u: Usuario): Boolean = try {
        dataBase.usuarioDao().insertUsuarioTask(u)
        true
    } catch (e: Exception) {
        false
    }

    override suspend fun deleteAll(): Boolean = try {
        dataBase.actividadDao().deleteAll()
        dataBase.subActividadDao().deleteAll()
        dataBase.parametroDao().deleteAll()
        true
    } catch (e: Exception) {
        false
    }


    override suspend fun saveSync(s: Sync): Boolean = try {
        val activities: List<Actividad>? = s.activities
        activities?.let {
            dataBase.actividadDao().insertActividadTask(it)
        }
        val subActivities: List<SubActividad>? = s.subActivities
        subActivities?.let {
            dataBase.subActividadDao().insertSubActividadTask(it)
        }
        val parameters: List<Parametro>? = s.parameters
        parameters?.let {
            dataBase.parametroDao().insertParametroTask(it)
        }
        true
    } catch (e: Exception) {
        false
    }

    override fun getRegisterWorkes(): Flow<List<RegisterWork>> =
        dataBase.registerWorkDao().getRegisterWorkes(active)
}