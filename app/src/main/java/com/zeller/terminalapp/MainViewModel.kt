package com.zeller.terminalapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zeller.domain.model.OperationResult
import com.zeller.domain.usecases.DepositCase
import com.zeller.domain.usecases.LoadBalanceCase
import com.zeller.domain.usecases.WithdrawCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val depositCase: DepositCase,
    private val withdrawCase: WithdrawCase,
    private val loadBalanceCase: LoadBalanceCase,
    application: Application
) : AndroidViewModel(application) {

    private val _balance = MutableLiveData<Float?>()
    val balance: LiveData<Float?> = _balance

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        loadBalance()
    }

    private fun loadBalance() {
        viewModelScope.launch {
            loadBalanceCase().collect {
                handleResult(it)
            }
        }
    }

    fun invokeDeposit(amount: Float) {
        viewModelScope.launch {
            depositCase(amount).collect {
                handleResult(it)
            }
        }
    }

    fun invokeWithdraw(amount: Float) {
        viewModelScope.launch {
            withdrawCase(amount).collect {
                handleResult(it)
            }
        }
    }

    private fun handleResult(result: OperationResult<Any>) {
        when (result) {
            is OperationResult.Success -> {
                _balance.value = result.data as Float
            }
            is OperationResult.NetworkError -> {
                _errorMessage.value = result.message
            }
            is OperationResult.EnterValidNumber -> {
                _errorMessage.value = getApplication<TerminalApp>().getString(R.string.enter_valid_number)
            }
            is OperationResult.NotEnoughBalance -> {
                _errorMessage.value = getApplication<TerminalApp>().getString(R.string.not_enough_balance)
            }
        }
    }
}