package com.zeller.terminalapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zeller.terminalapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.depositButton.setOnClickListener(this)
        binding.withdrawButton.setOnClickListener(this)
        setContentView(binding.root)
    }

    override fun onClick(view: View?) {
        val amt: Float
        when(view?.id) {
            R.id.withdrawButton -> {
                val balance = MainViewModel.balance
                if (!binding.amountInput.text.isNullOrEmpty()) {
                    amt = binding.amountInput.text.toString().toFloat()
                    if (balance >= amt) {
                        MainViewModel.balance -= amt
                        binding.balance.text = MainViewModel.balance.toString()
                        MainViewModel.transactions.addTransaction(Transactions(isDeposit = false, amount = amt))
                    } else {
                        Toast.makeText(this, R.string.not_enough_balance, Toast.LENGTH_LONG).show()
                    }
                }
            }
            R.id.depositButton -> {
                if (!binding.amountInput.text.isNullOrEmpty()) {
                    amt = binding.amountInput.text.toString().toFloat()
                    if (amt == 0f) {
                        Toast.makeText(this, R.string.deposit_at_least, Toast.LENGTH_LONG).show()
                        return
                    }
                    MainViewModel.balance += amt
                    binding.balance.text = MainViewModel.balance.toString()
                    MainViewModel.transactions.addTransaction(Transactions(isDeposit = true, amount = amt))
                }
            }
        }
    }
}