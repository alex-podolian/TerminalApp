package com.zeller.domain.usecases

import com.zeller.domain.model.TransactionsList
import kotlinx.coroutines.flow.Flow

interface LoadTransactionsCase {
    suspend operator fun invoke(id: String): Flow<TransactionsList>
}