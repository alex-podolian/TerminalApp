package com.zeller.terminalapp.presentation.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zeller.domain.model.Transaction
import com.zeller.domain.usecases.LoadTransactionsCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionsViewModel@Inject constructor(
    private val loadTransactionsCase: LoadTransactionsCase,
) : ViewModel() {

    private val _transactions = MutableLiveData<List<Transaction>?>()
    val transactions: LiveData<List<Transaction>?> = _transactions

    private val _isEmptyList = MutableLiveData<Boolean>()
    val isEmptyList: LiveData<Boolean> = _isEmptyList

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            loadTransactionsCase("transactionsList").collect {
                if (it.transactions.isEmpty()) {
                    _isEmptyList.value = true
                }
                _transactions.value = it.transactions
            }
        }
    }
}