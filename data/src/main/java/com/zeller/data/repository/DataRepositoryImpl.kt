package com.zeller.data.repository

import com.zeller.data.local.mapper.toDomain
import com.zeller.data.local.mapper.toLocal
import com.zeller.data.local.model.LocalBalance
import com.zeller.data.utils.EnterValidNumberException
import com.zeller.data.utils.NotEnoughBalanceException
import com.zeller.domain.model.Balance
import com.zeller.domain.model.Transaction
import com.zeller.domain.model.TransactionsList
import com.zeller.domain.repository.DataRepository
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource,
) : DataRepository {

    private var balance = 0f
    private var transactionsList: MutableList<Transaction> = mutableListOf()

    override suspend fun deposit(amount: Float): Balance {
        return if (amount < 0.01f) {
            throw EnterValidNumberException()
        } else {
            balance += amount
            transactionsList.add(Transaction(amount = amount, isDeposit = true))
            localSource.persistTransaction(TransactionsList(transactions = transactionsList).toLocal())
            localSource.persistBalance(LocalBalance(balance = balance))
            Balance(balance = balance)
        }
    }

    override suspend fun withdraw(amount: Float): Balance {
        return if (balance >= amount && amount != 0f) {
            balance -= amount
            transactionsList.add(Transaction(amount = amount, isDeposit = false))
            localSource.persistTransaction(TransactionsList(transactions = transactionsList).toLocal())
            localSource.persistBalance(LocalBalance(balance = balance))
            Balance(balance = balance)
        } else {
            if (balance <= amount) {
                throw NotEnoughBalanceException()
            } else {
                throw EnterValidNumberException()
            }
        }
    }

    override suspend fun loadBalance(id: String): Balance {
        remoteSource.fetchBalance(id)
            .catch {
                localSource.retrieveBalance(id)?.balance?.let {
                    balance = it
                }
            }
            .collect {
                balance = it.balance
            }
        return Balance(balance = balance)
    }

    override suspend fun loadTransactions(id: String): TransactionsList {
        localSource.retrieveTransactions(id)?.toDomain()?.transactions?.let {
            transactionsList = mutableListOf()
            transactionsList.addAll(it)
        }
        return TransactionsList(transactions = transactionsList)
    }
}