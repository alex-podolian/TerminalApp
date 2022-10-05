package com.zeller.terminalapp.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zeller.terminalapp.R
import com.zeller.terminalapp.app.TerminalApp
import com.zeller.terminalapp.databinding.ActivityMainBinding
import com.zeller.terminalapp.presentation.transactions.TransactionsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TerminalApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.depositButton.setOnClickListener(this)
        binding.withdrawButton.setOnClickListener(this)
        binding.showTransactionsButton.setOnClickListener(this)
        setContentView(binding.root)
        setupViewModel()
    }

    private val balanceObserver = Observer<String?> { balance ->
        binding.balance.text = balance
    }

    private val errorObserver = Observer<String?> { message ->
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setupViewModel() {
        viewModel.balance.observe(this, balanceObserver)
        viewModel.errorMessage.observe(this, errorObserver)
    }

    override fun onClick(view: View?) {
        binding.amountInput.text?.apply {
            when (view?.id) {
                R.id.withdrawButton -> {
                    viewModel.invokeWithdraw(this)
                }
                R.id.depositButton -> {
                    viewModel.invokeDeposit(this)
                }
                R.id.showTransactionsButton -> {
                    this@MainActivity.startActivity(
                        Intent(
                            this@MainActivity,
                            TransactionsActivity::class.java
                        )
                    )
                }
            }
        }
    }
}