package com.zeller.domain.model

data class Transaction(
    val amount: Float,
    val isDeposit: Boolean
)