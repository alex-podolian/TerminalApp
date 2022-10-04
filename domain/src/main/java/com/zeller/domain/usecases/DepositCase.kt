package com.zeller.domain.usecases

import com.zeller.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface DepositCase {
    suspend operator fun invoke(amount: Float): Flow<Boolean>
}

class DepositCaseImpl(private val dataRepository: DataRepository) : DepositCase {

    override suspend fun invoke(amount: Float): Flow<Boolean> = flow {
        emit(dataRepository.deposit(amount))
    }
}