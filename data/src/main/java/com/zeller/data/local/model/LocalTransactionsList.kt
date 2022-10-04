package com.zeller.data.local.model

data class LocalTransactionsList(
    val id: String = "transactionsList",
    val transactions: List<LocalTransaction>
)