package com.salim.penjualan3.ui.user

import com.salim.penjualan3.data.database.PrefsManager

class UserPresenter (val view : UserContract.View): UserContract.Presenter {

    init {
        view.initActivity()
        view.initListener()
    }

    override fun doLogin(prefsManager: PrefsManager) {
        if (prefsManager.prefsIsLogin) view.onResultLogin(prefsManager)
    }

    override fun doLogout(prefsManager: PrefsManager) {
        prefsManager.logout()
        view.showMessage("Berhasil keluar")
        view.onResultLogout()
    }
}