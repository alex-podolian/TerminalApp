package com.zeller.terminalapp.presentation.transactions

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeller.domain.model.Transaction
import com.zeller.terminalapp.app.TerminalApp
import com.zeller.terminalapp.databinding.ActivityMainBinding
import com.zeller.terminalapp.databinding.ActivityTransactionsBinding
import com.zeller.terminalapp.databinding.LayoutEmptyBinding
import javax.inject.Inject

class TransactionsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TransactionsViewModel
    private lateinit var binding: ActivityTransactionsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyList: TextView
    private lateinit var adapter: TransactionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TerminalApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        emptyList = binding.emptyList

        setupViewModel()
        setupUI()
    }

    private val renderTransactions = Observer<List<Transaction>?> {
        emptyList.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.update(it)
    }

    private val emptyListObserver = Observer<Boolean> {
        emptyList.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun setupViewModel() {
        viewModel.transactions.observe(this, renderTransactions)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    private fun setupUI() {
        adapter = TransactionsAdapter(viewModel.transactions.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}