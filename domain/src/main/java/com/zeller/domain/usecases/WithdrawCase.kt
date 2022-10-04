package com.zeller.domain.usecases

import com.zeller.domain.model.Balance
import com.zeller.domain.repository.OperationResult
import kotlinx.coroutines.flow.Flow

interface WithdrawCase {
    suspend operator fun invoke(amount: Float): Flow<OperationResult<Balance>>
}