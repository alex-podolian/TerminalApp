package com.zeller.network


import com.zeller.data.remote.model.RemoteBalance
import com.zeller.data.repository.RemoteSource
import com.zeller.network.mapper.toRemote
import com.zeller.network.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkSource @Inject constructor(
    private val apiService: ApiService,
) : RemoteSource {

    override suspend fun fetchBalance(id: String): Flow<RemoteBalance> {
        return withContext(Dispatchers.IO) {
            flow {
                emit(apiService.fetchBalance(id).toRemote())
            }
        }
    }
}