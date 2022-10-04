package com.zeller.data.di

import com.zeller.data.repository.DataRepositoryImpl
import com.zeller.data.usecases.DepositCaseImpl
import com.zeller.data.usecases.LoadBalanceCaseImpl
import com.zeller.data.usecases.WithdrawCaseImpl
import com.zeller.domain.repository.DataRepository
import com.zeller.domain.usecases.DepositCase
import com.zeller.domain.usecases.LoadBalanceCase
import com.zeller.domain.usecases.WithdrawCase
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideRepository(dataRepository: DataRepositoryImpl): DataRepository

    @Binds
    abstract fun provideDepositCase(depositCase: DepositCaseImpl): DepositCase

    @Binds
    abstract fun provideWithdrawCase(withdrawCase: WithdrawCaseImpl): WithdrawCase

    @Binds
    abstract fun provideLoadBalanceCase(loadBalanceCase: LoadBalanceCaseImpl): LoadBalanceCase
}