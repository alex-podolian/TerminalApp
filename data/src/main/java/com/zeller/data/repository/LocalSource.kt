package com.zeller.data.repository

import com.zeller.data.local.model.LocalBalance
import com.zeller.data.local.model.LocalTransactionsList

interface LocalSource {
    suspend fun retrieveBalance(id: String): LocalBalance?
    suspend fun persistBalance(localBalance: LocalBalance)
    suspend fun retrieveTransactions(id: String) : LocalTransactionsList?
    suspend fun persistTransaction(localTransactionsList: LocalTransactionsList)
}