package com.zeller.network.mapper

import com.zeller.data.remote.model.RemoteBalance
import com.zeller.data.remote.model.RemoteTransaction
import com.zeller.data.remote.model.RemoteTransactionsList
import com.zeller.network.model.NetworkBalance
import com.zeller.network.model.NetworkTransaction
import com.zeller.network.model.NetworkTransactionsList

internal fun NetworkBalance.toRemote() = RemoteBalance(
    id = this.id,
    balance = this.balance,
)

internal fun NetworkTransaction.toRemote() = RemoteTransaction(
    amount = this.amount,
    isDeposit = this.isDeposit
)

internal fun NetworkTransactionsList.toRemote() = RemoteTransactionsList(
    id = this.id,
    transactions = this.transactions.map { it.toRemote() }
)
