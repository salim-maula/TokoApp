package com.salim.penjualan3.ui.transaction

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.salim.penjualan3.R
import com.salim.penjualan3.data.model.transaction.DataTransaction
import kotlinx.android.synthetic.main.adapter_transaction.view.*

class TransactionAdapter (val context: Context, var transaction:ArrayList<DataTransaction>,
                          val clickListener: (DataTransaction, Int)->Unit):
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(transaction:DataTransaction){
            view.txvInvoice.text = transaction.no_faktur
            view.txvDate.text = transaction.tgl_penjualan
            view.txvTotal.text = transaction.total_rupiah
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_transaction, parent, false)
    )

    override fun getItemCount() = transaction.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(transaction[position])
        holder.view.txvSee.setOnClickListener {
            clickListener(transaction[position],position)
        }
    }

    fun setData(newTransaction: List<DataTransaction>){
        transaction.clear()
        transaction.addAll(newTransaction)
        notifyDataSetChanged()
    }

}
























