package com.salim.penjualan3.ui.transaction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.salim.penjualan3.R

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        initActivity()
    }

    fun initActivity(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, TransactionFragment(), "tag_fragment")
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}