package com.dsige.cca.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsige.cca.data.dataResult.OperationResult
import com.dsige.cca.data.model.Usuario
import com.dsige.cca.usesCase.checkSessionUsesCase.CheckSessionUsesCase
import com.dsige.cca.usesCase.loginUsesCase.LoginLocalUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CheckSessionViewModel @Inject
constructor(
    private val checkSessionUsesCase: CheckSessionUsesCase,
) :
    ViewModel() {

    private val _usuario: MutableStateFlow<Usuario?> =
        MutableStateFlow(null)
    val usuario: StateFlow<Usuario?> = _usuario

    fun verifySignIn() {
        viewModelScope.launch {
            checkSessionUsesCase.invoke().flowOn(Dispatchers.IO).onStart {
                delay(1000)
            }.collect {
                _usuario.value = it
            }
        }
    }
}