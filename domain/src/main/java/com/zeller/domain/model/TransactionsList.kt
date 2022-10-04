package com.zeller.domain.model

data class TransactionsList(
    val id: String = "transactionsList",
    val transactions: List<Transaction>
)