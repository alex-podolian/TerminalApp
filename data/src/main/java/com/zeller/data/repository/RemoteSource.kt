package com.zeller.data.repository

import com.zeller.data.remote.model.RemoteBalance
import kotlinx.coroutines.flow.Flow


interface RemoteSource {
    suspend fun fetchBalance(id: String): Flow<RemoteBalance>
}