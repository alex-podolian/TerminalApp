package com.zeller.data.remote.mapper

import com.zeller.data.remote.model.RemoteBalance
import com.zeller.data.remote.model.RemoteTransaction
import com.zeller.data.remote.model.RemoteTransactionsList
import com.zeller.domain.model.Balance
import com.zeller.domain.model.Transaction
import com.zeller.domain.model.TransactionsList

internal fun RemoteBalance.toDomain() = Balance(
    id = this.id,
    balance = this.balance,
)

internal fun RemoteTransaction.toDomain() = Transaction(
    amount = this.amount,
    isDeposit = this.isDeposit
)

internal fun RemoteTransactionsList.toDomain() = TransactionsList(
    id = this.id,
    transactions = this.transactions.map { it.toDomain() }
)