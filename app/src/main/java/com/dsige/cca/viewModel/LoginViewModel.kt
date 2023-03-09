package com.dsige.cca.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsige.cca.data.model.Sync
import com.dsige.cca.data.model.Usuario
import com.dsige.cca.usesCase.loginUsesCase.LoginLocalUsesCase
import com.dsige.cca.usesCase.loginUsesCase.LoginRestUsesCase
import com.dsige.cca.usesCase.syncUsesCase.SyncLocalUsesCase
import com.dsige.cca.usesCase.syncUsesCase.SyncRestUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject
constructor(
    private val loginLocalUsesCase: LoginLocalUsesCase,
    private val loginRestUsesCase: LoginRestUsesCase,
    private val syncLocalUsesCase: SyncLocalUsesCase,
    private val syncRestUsesCase: SyncRestUsesCase,
) :
    ViewModel() {

    private val _mensajeError: MutableStateFlow<String?> = MutableStateFlow(null)
    val mensajeError: StateFlow<String?> = _mensajeError

    private val _mensajeSuccess: MutableStateFlow<String?> = MutableStateFlow(null)
    val mensajeSuccess: StateFlow<String?> = _mensajeSuccess

    fun setError(s: String?) {
        viewModelScope.launch {
            _mensajeError.value = s
        }
    }

    private fun setSuccess(s: String?) {
        viewModelScope.launch {
            _mensajeSuccess.value = s
        }
    }

    fun setLogin(model: MutableMap<String, Any>) {
        viewModelScope.launch {
            loginRestUsesCase.invoke(model).flowOn(Dispatchers.IO).catch { e ->
                if (e is HttpException) {
                    setError(e.response()?.errorBody().toString())
                } else {
                    setError(e.message)
                }
            }.collect { user ->
                insertUsuario(user)
            }
        }
    }

    private fun insertUsuario(u: Usuario) {
        viewModelScope.launch {
            val success = loginLocalUsesCase.invoke(u)
            if (success) {
                setSync()
            }
        }
    }

    //    fun getSync(codUsuario: String, tipo: Int) {
    private fun setSync() {
        viewModelScope.launch {
            val success = syncLocalUsesCase.invoke()
            if (success) {
                getSync()
            }
        }
    }

    private fun getSync() {
        viewModelScope.launch {
            syncRestUsesCase.invoke()
                .flowOn(Dispatchers.IO).catch { e ->
                    if (e is HttpException) {
                        setError(e.response()?.errorBody().toString())
                    } else {
                        setError(e.message)
                    }
                }.collect { sync ->
                    insertSync(sync)
                }
        }
    }

    private fun insertSync(s: Sync) {
        viewModelScope.launch {
            val success = syncLocalUsesCase.invoke(s)
            if (success) {
                setSuccess("Sincronización Completa")
            } else {
                setError("Error inesperado")
            }
        }
    }
}