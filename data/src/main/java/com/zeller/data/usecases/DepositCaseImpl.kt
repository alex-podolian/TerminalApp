package com.zeller.data.usecases

import com.zeller.domain.model.Balance
import com.zeller.domain.repository.OperationResult
import com.zeller.domain.repository.DataRepository
import com.zeller.domain.repository.asResult
import com.zeller.domain.usecases.DepositCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DepositCaseImpl @Inject constructor(private val dataRepository: DataRepository) :
    DepositCase {

    override suspend fun invoke(amount: Float): Flow<OperationResult<Balance>> = flow {
        emit(dataRepository.deposit(amount))
    }.asResult()
}