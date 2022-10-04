package com.zeller.domain.usecases

import com.zeller.domain.model.OperationResult
import kotlinx.coroutines.flow.Flow

interface DepositCase {
    suspend operator fun invoke(amount: Float): Flow<OperationResult<Any>>
}