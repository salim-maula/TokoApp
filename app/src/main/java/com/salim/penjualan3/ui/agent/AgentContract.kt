package com.salim.penjualan3.ui.agent

import com.salim.penjualan3.data.model.agent.DataAgent
import com.salim.penjualan3.data.model.agent.ResponseAgentList
import com.salim.penjualan3.data.model.agent.ResponseAgentUpdate

interface AgentContract {

    interface Presenter {
        fun getAgent()
        fun deleteAgent(kd_agen: Long)
    }

    interface View {
        fun initActivity()
        fun initListener()
        fun onLoadingAgent(loading: Boolean)
        fun onResultAgent(responseAgentList: ResponseAgentList)
        fun showMessage(message: String)
        fun showDialogDelete(dataAgent: DataAgent, position:Int)
        fun onResultDelete(responseAgentUpdate: ResponseAgentUpdate)
        fun showDialogDetail(dataAgent: DataAgent, position:Int)

    }
}



















