package com.zeller.domain.usecases

import com.zeller.domain.model.OperationResult
import kotlinx.coroutines.flow.Flow

interface LoadBalanceCase {
    suspend operator fun invoke(): Flow<OperationResult<Any>>
}