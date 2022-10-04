package com.zeller.data.repository


interface RemoteSource {
    suspend fun fetchData(): Float
}