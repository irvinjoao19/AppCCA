package com.dsige.cca.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsige.cca.data.model.RegisterWork
import com.dsige.cca.data.model.Usuario
import com.dsige.cca.usesCase.workUsesCase.WorkLocalUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject
constructor(
    private val workLocalUsesCase: WorkLocalUsesCase,
) :
    ViewModel() {

    private val _registerWork: MutableStateFlow<List<RegisterWork>> =
        MutableStateFlow(emptyList())
    val registerWork: StateFlow<List<RegisterWork>> = _registerWork

    fun setRegisterWork() {
        viewModelScope.launch {
            workLocalUsesCase.invoke().flowOn(Dispatchers.IO).onStart {
                delay(1000)
            }.collect {
                _registerWork.value = it
            }
        }
    }
}