package com.zeller.network.model

data class NetworkTransactionsList(
    val id: String = "transactionsList",
    val transactions: List<NetworkTransaction>
)