package com.zeller.domain.repository

interface DataRepository {
    suspend fun deposit(amount: Float): Boolean
    suspend fun withdraw(amount: Float) : Boolean
}