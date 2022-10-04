package com.zeller.domain.repository

import com.zeller.domain.model.Balance

interface DataRepository {
    suspend fun deposit(amount: Float): Balance
    suspend fun withdraw(amount: Float) : Balance
    suspend fun loadBalance() : Balance
}