package com.zeller.terminalapp.presentation.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeller.domain.model.Transaction
import com.zeller.terminalapp.R

class TransactionsAdapter(private var transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionsAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_transaction, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        vh.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    fun update(data: List<Transaction>) {
        transactions = data
        notifyDataSetChanged()
    }

    class MViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val transactionType: TextView = view.findViewById(R.id.transactionType)
        private val amount: TextView = view.findViewById(R.id.amount)
        fun bind(transaction: Transaction) {
            if (transaction.isDeposit) {
                transactionType.text = view.context.getString(R.string.deposit)
                amount.text = "+${transaction.amount}"
            } else {
                transactionType.text = view.context.getString(R.string.withdraw)
                amount.text = "-${transaction.amount}"
            }
        }
    }
}