package com.zeller.domain.repository

import com.zeller.domain.model.OperationResult

interface DataRepository {
    suspend fun deposit(amount: Float): OperationResult<Any>
    suspend fun withdraw(amount: Float) : OperationResult<Any>
    suspend fun loadBalance() : OperationResult<Any>
}