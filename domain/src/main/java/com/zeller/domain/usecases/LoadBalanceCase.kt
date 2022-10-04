package com.zeller.domain.usecases

import com.zeller.domain.model.Balance
import kotlinx.coroutines.flow.Flow

interface LoadBalanceCase {
    suspend operator fun invoke(): Flow<Balance?>
}