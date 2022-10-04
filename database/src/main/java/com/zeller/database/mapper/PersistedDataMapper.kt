package com.zeller.database.mapper

import com.zeller.data.local.model.LocalBalance
import com.zeller.data.local.model.LocalTransaction
import com.zeller.data.local.model.LocalTransactionsList
import com.zeller.database.model.PersistedBalance
import com.zeller.database.model.PersistedTransaction
import com.zeller.database.model.PersistedTransactionsList
import io.realm.kotlin.ext.toRealmList

internal fun LocalBalance.toDatabase() = PersistedBalance(
    id = this.id,
    balance = this.balance,
)

internal fun LocalTransaction.toDatabase() = PersistedTransaction(
    amount = this.amount,
    isDeposit = this.isDeposit
)

internal fun LocalTransactionsList.toDatabase() = PersistedTransactionsList(
    id = this.id,
    transactions = this.transactions.map { it.toDatabase() }.toRealmList()
)

internal fun PersistedBalance.toLocal() = LocalBalance(
    id = this.id,
    balance = this.balance
)

internal fun PersistedTransaction.toLocal() = LocalTransaction(
    amount = this.amount,
    isDeposit = this.isDeposit
)

internal fun PersistedTransactionsList.toLocal() = LocalTransactionsList(
    id = this.id,
    transactions = this.transactions.map { it.toLocal() }
)
