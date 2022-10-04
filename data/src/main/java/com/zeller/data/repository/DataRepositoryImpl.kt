package com.zeller.data.repository

import com.zeller.domain.repository.DataRepository

class DataRepositoryImpl() : DataRepository {

    override suspend fun deposit(amount: Float): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun withdraw(amount: Float): Boolean {
        TODO("Not yet implemented")
    }
}