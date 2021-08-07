package com.salim.penjualan3.ui.home

class MainPresenter (val view : MainContract.View) {

    init {
        view.initListener()
    }
}