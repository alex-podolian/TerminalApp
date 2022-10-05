package com.zeller.data.usecases

import com.zeller.domain.model.TransactionsList
import com.zeller.domain.repository.DataRepository
import com.zeller.domain.usecases.LoadTransactionsCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadTransactionsCaseImpl @Inject constructor(private val dataRepository: DataRepository) :
    LoadTransactionsCase {

    override suspend fun invoke(id: String): Flow<TransactionsList> = flow {
        emit(dataRepository.loadTransactions(id))
    }
}