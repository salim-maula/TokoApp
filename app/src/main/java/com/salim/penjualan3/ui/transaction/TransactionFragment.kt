package com.salim.penjualan3.ui.transaction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.salim.penjualan3.R
import com.salim.penjualan3.data.Constant
import com.salim.penjualan3.data.database.PrefsManager
import com.salim.penjualan3.data.model.transaction.DataTransaction
import com.salim.penjualan3.data.model.transaction.ResponseTransactionList
import kotlinx.android.synthetic.main.content_agent.*


class TransactionFragment : Fragment(), TransactionContract.View {

    lateinit var prefsManager: PrefsManager
    lateinit var transactionAdapter: TransactionAdapter
    lateinit var presenter: TransactionPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)

        prefsManager = PrefsManager(requireContext())
        presenter = TransactionPresenter(this)
        initListener(view)

        return view
    }

    override fun initFragment() {
        transactionAdapter = TransactionAdapter(requireContext(), arrayListOf()){
            dataTransaction, position ->
            onClickTransaction( dataTransaction.no_faktur!!)
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as AppCompatActivity).supportActionBar!!.title = "Transaksi"
        presenter.getTransactionByUsername(prefsManager.prefsUsername)
    }

    override fun initListener(view: View) {
        val rcvTransaction = view.findViewById<RecyclerView>(R.id.rcvTransaction)
        val swipe = view.findViewById<SwipeRefreshLayout>(R.id.swipe)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        rcvTransaction!!.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionAdapter
        }
        swipe.setOnRefreshListener {
            presenter.getTransactionByUsername(prefsManager.prefsUsername)
        }
        fab.setOnClickListener {

        }
    }

    override fun onLoading(loading: Boolean) {
       when(loading){
           true->swipe.isRefreshing = true
           false->swipe.isRefreshing = false
       }
    }

    override fun onResult(responseTransactionList: ResponseTransactionList) {
       val dataTransaction: List<DataTransaction> = responseTransactionList.dataTransaction
        transactionAdapter.setData(dataTransaction)
    }

    override fun onClickTransaction(invoice: String) {
       Constant.INVOICE = invoice
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, TransactionDetailFragment(), "transDetail")
            .commit()
    }




}