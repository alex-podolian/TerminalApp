package com.zeller.data.repository

import com.zeller.data.local.model.Transactions
import com.zeller.data.local.model.TransactionsList
import com.zeller.domain.model.OperationResult
import com.zeller.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor() : DataRepository {

    private var balance = 0f
    var transactions: TransactionsList = TransactionsList()

    override suspend fun deposit(amount: Float): OperationResult<Any> {
        return if (amount == 0f) {
            OperationResult.EnterValidNumber
        } else {
            balance += amount
            transactions.addTransaction(Transactions(isDeposit = true, amount = amount))
            OperationResult.Success(balance)
        }
    }

    override suspend fun withdraw(amount: Float): OperationResult<Any> {
        return if (balance >= amount && amount != 0f) {
            balance -= amount
            transactions.addTransaction(Transactions(isDeposit = false, amount = amount))
            OperationResult.Success(balance)
        } else {
            OperationResult.EnterValidNumber
        }
    }

    override suspend fun loadBalance(): OperationResult<Any> {
        return OperationResult.Success(balance)
    }
}