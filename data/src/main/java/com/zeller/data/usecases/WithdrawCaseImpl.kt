package com.zeller.data.usecases

import com.zeller.domain.model.Balance
import com.zeller.domain.repository.OperationResult
import com.zeller.domain.repository.DataRepository
import com.zeller.domain.repository.asResult
import com.zeller.domain.usecases.WithdrawCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class WithdrawCaseImpl @Inject constructor(private val dataRepository: DataRepository) :
    WithdrawCase {

    override suspend fun invoke(amount: Float): Flow<OperationResult<Balance>> = flow {
        emit(dataRepository.withdraw(amount))
    }.asResult()
}