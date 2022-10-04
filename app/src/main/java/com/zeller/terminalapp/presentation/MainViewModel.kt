package com.zeller.terminalapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zeller.data.utils.EnterValidNumberException
import com.zeller.data.utils.NotEnoughBalanceException
import com.zeller.domain.model.Balance
import com.zeller.domain.repository.OperationResult
import com.zeller.domain.usecases.DepositCase
import com.zeller.domain.usecases.LoadBalanceCase
import com.zeller.domain.usecases.WithdrawCase
import com.zeller.terminalapp.R
import com.zeller.terminalapp.app.TerminalApp
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val depositCase: DepositCase,
    private val withdrawCase: WithdrawCase,
    private val loadBalanceCase: LoadBalanceCase,
    application: Application
) : AndroidViewModel(application) {

    private val _balance = MutableLiveData<String?>()
    val balance: LiveData<String?> = _balance

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    init {
        loadBalance()
    }

    private fun loadBalance() {
        viewModelScope.launch {
            loadBalanceCase().collect {
                _balance.value = it?.balance.toString()
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

    private fun handleResult(result: OperationResult<Balance>) {
        when (result) {
            is OperationResult.Success -> {
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.UP
                val roundedBalance = df.format(result.data.balance)
                _balance.value = roundedBalance
            }
            is OperationResult.Failure -> {
                when(result.exception) {
                    is NotEnoughBalanceException -> {
                        _errorMessage.value = getApplication<TerminalApp>().getString(R.string.not_enough_balance)
                    }
                    is EnterValidNumberException -> {
                        _errorMessage.value = getApplication<TerminalApp>().getString(R.string.enter_valid_number)
                    }
                    else -> {
                        _errorMessage.value = result.exception?.message
                    }
                }
            }
        }
    }
}