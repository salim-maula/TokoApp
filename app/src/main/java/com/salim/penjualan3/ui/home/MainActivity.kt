package com.salim.penjualan3.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.salim.penjualan3.R
import com.salim.penjualan3.data.database.PrefsManager
import com.salim.penjualan3.ui.agent.AgentActivity
import com.salim.penjualan3.ui.login.LoginActivity
import com.salim.penjualan3.ui.transaction.TransactionActivity
import com.salim.penjualan3.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_login.btnLogin
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    lateinit var presenter: MainPresenter
    lateinit var prefsManager: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        prefsManager = PrefsManager(this)
    }

    override fun onStart() {
        super.onStart()
        when(prefsManager.prefsIsLogin){
            true -> {
                crvUser.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
            }
            false -> {
                crvUser.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
            }
        }
    }


    override fun initListener() {
        crvTransaction.setOnClickListener {
            if (prefsManager.prefsIsLogin)
                startActivity(Intent(this, TransactionActivity::class.java))
            else
                showMessage("Anda belum login")
        }
        crvAgent.setOnClickListener {
            if (prefsManager.prefsIsLogin)
                startActivity(Intent(this, AgentActivity::class.java))
            else
                showMessage("Anda belum login")
        }
        btnLogin.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
        crvUser.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}