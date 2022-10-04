package com.zeller.data.repository

import com.zeller.data.local.model.LocalBalance

interface LocalSource {
    suspend fun retrieveBalance(): LocalBalance?
    suspend fun persistBalance(localBalance: LocalBalance)
}