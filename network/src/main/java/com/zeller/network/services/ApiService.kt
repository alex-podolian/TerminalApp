package com.zeller.network.services

import com.zeller.network.model.NetworkBalance
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/fetch/user-balance")
    suspend fun fetchBalance(
        @Path("id") id: String,
    ): NetworkBalance
}