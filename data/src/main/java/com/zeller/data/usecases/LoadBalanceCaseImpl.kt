package com.zeller.data.usecases

import com.zeller.domain.model.OperationResult
import com.zeller.domain.repository.DataRepository
import com.zeller.domain.usecases.LoadBalanceCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadBalanceCaseImpl @Inject constructor(private val dataRepository: DataRepository) :
    LoadBalanceCase {

    override suspend fun invoke(): Flow<OperationResult<Any>> = flow {
        emit(dataRepository.loadBalance())
    }
}