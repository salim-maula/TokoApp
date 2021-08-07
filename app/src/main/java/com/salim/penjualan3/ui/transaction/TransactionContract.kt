package com.salim.penjualan3.ui.transaction

import com.salim.penjualan3.data.model.transaction.ResponseTransactionList

interface TransactionContract {

    interface presenter{
        fun getTransactionByUsername(username: String)
    }

    interface View{
        fun initFragment()
        fun initListener(view: android.view.View)
        fun onLoading(loading: Boolean)
        fun onResult(responseTransactionList: ResponseTransactionList)
        fun onClickTransaction(invoice: String)
    }

}