package com.zeller.domain.usecases

import com.zeller.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface WithdrawCase {
    suspend operator fun invoke(amount: Float): Flow<Boolean>
}

class WithdrawCaseImpl(private val dataRepository: DataRepository) : WithdrawCase {

    override suspend fun invoke(amount: Float): Flow<Boolean> = flow {
        emit(dataRepository.withdraw(amount))
    }
}