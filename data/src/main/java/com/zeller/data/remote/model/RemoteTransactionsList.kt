package com.zeller.data.remote.model

data class RemoteTransactionsList(
    val id: String = "transactionsList",
    val transactions: List<RemoteTransaction>
)