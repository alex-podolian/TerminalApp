package com.zeller.data.local.mapper

import com.zeller.data.local.model.LocalBalance
import com.zeller.data.local.model.LocalTransaction
import com.zeller.data.local.model.LocalTransactionsList
import com.zeller.domain.model.Balance
import com.zeller.domain.model.Transaction
import com.zeller.domain.model.TransactionsList

internal fun LocalBalance.toDomain() = Balance(
    id = this.id,
    balance = this.balance,
)

internal fun LocalTransaction.toDomain() = Transaction(
    amount = this.amount,
    isDeposit = this.isDeposit
)

internal fun LocalTransactionsList.toDomain() = TransactionsList(
    id = this.id,
    transactions = this.transactions.map { it.toDomain() }
)

internal fun Balance.toLocal() = LocalBalance(
    id = this.id,
    balance = this.balance
)

internal fun Transaction.toLocal() = LocalTransaction(
    amount = this.amount,
    isDeposit = this.isDeposit
)

internal fun TransactionsList.toLocal() = LocalTransactionsList(
    id = this.id,
    transactions = this.transactions.map { it.toLocal() }
)
