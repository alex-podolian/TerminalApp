package com.zeller.data.repository

import com.zeller.data.local.model.LocalBalance
import com.zeller.data.utils.EnterValidNumberException
import com.zeller.data.utils.NotEnoughBalanceException
import com.zeller.domain.model.Balance
import com.zeller.domain.repository.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
) : DataRepository {

    private var balance = 0f

    override suspend fun deposit(amount: Float): Balance {
        return if (amount < 0.01f) {
            throw EnterValidNumberException()
        } else {
            balance += amount
            localSource.persistBalance(LocalBalance(balance = balance))
            Balance(balance = balance)
        }
    }

    override suspend fun withdraw(amount: Float): Balance {
        return if (balance >= amount && amount != 0f) {
            balance -= amount
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

    override suspend fun loadBalance(): Balance {
        localSource.retrieveBalance()?.balance?.let {
            balance = it
        }
        return Balance(balance = balance)
    }
}