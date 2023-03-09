package com.dsige.cca.data.dataResult

sealed class OperationResult<out T> {
    object First : OperationResult<Nothing>()
    data class Loading(val message: String) : OperationResult<Nothing>()
    data class Error(val code: Int, val title: String, val message: String?) :
        OperationResult<Nothing>()

    data class Success<out R>(val data: R) : OperationResult<R>()
}