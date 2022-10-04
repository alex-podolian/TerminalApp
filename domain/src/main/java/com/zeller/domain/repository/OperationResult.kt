package com.zeller.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

sealed interface OperationResult<out T> {
    data class Success<T>(val data: T) : OperationResult<T>
    data class Failure(val exception: Throwable? = null) : OperationResult<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<OperationResult<T>> {
    return this
        .map<T, OperationResult<T>> {
            OperationResult.Success(it)
        }
        .catch { emit(OperationResult.Failure(it)) }
}