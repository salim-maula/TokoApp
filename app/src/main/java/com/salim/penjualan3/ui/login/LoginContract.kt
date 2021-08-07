package com.salim.penjualan3.ui.login

import com.salim.penjualan3.data.database.PrefsManager
import com.salim.penjualan3.data.model.login.DataLogin
import com.salim.penjualan3.data.model.login.ResponseLogin

interface LoginContract {

    interface Presenter {
        fun doLogin(username:String, password:String)
        fun setPrefs(prefsManager: PrefsManager, dataLogin: DataLogin)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoading(loading: Boolean)
        fun onResult(responseLogin: ResponseLogin)
        fun showMessage(message: String)
    }

}