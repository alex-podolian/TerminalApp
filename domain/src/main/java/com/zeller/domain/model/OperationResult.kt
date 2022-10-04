package com.zeller.domain.model

sealed interface OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>
    data class NetworkError(val message: String? = null) : OperationResult<Nothing>
    object NotEnoughBalance : OperationResult<Nothing>
    object EnterValidNumber : OperationResult<Nothing>
}