package com.zeller.domain.repository

import com.zeller.domain.model.Balance
import com.zeller.domain.model.TransactionsList

interface DataRepository {
    suspend fun deposit(amount: Float): Balance
    suspend fun withdraw(amount: Float) : Balance
    suspend fun loadBalance(id: String) : Balance
    suspend fun loadTransactions(id: String) : TransactionsList
}