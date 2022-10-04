package com.zeller.terminalapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zeller.terminalapp.databinding.ActivityMainBinding
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
        setContentView(binding.root)
        setupViewModel()
    }

    private val balanceObserver = Observer<Float?> { balance ->
        binding.balance.text = balance.toString()
    }

    private val errorObserver = Observer<String?> { message ->
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setupViewModel() {
        viewModel.balance.observe(this, balanceObserver)
        viewModel.errorMessage.observe(this, errorObserver)
    }

    override fun onClick(view: View?) {
        binding.amountInput.text.apply {
            if (!this.isNullOrEmpty()) {
                when (view?.id) {
                    R.id.withdrawButton -> {
                        viewModel.invokeWithdraw(this.toString().toFloat())
                    }
                    R.id.depositButton -> {
                        viewModel.invokeDeposit(this.toString().toFloat())
                    }
                }
            }
        }
    }
}