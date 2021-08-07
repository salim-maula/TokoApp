package com.salim.penjualan3.ui.agent

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salim.penjualan3.R
import com.salim.penjualan3.data.Constant
import com.salim.penjualan3.data.model.agent.DataAgent
import com.salim.penjualan3.utils.GlideHelper
import kotlinx.android.synthetic.main.adapter_agent.view.*

class AgentAdapter (val context: Context, var dataAgent:ArrayList<DataAgent>,
                    val clickListener: (DataAgent, Int, String)->Unit):
    RecyclerView.Adapter<AgentAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataAgent:DataAgent){
            view.txvNameStore.text = dataAgent.nama_toko
            view.txvLocation.text = dataAgent.alamat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_agent, parent, false)
    )

    override fun getItemCount() = dataAgent.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataAgent[position])
        GlideHelper.setImage(context, dataAgent[position].gambar_toko!!, holder.view.imvImage)

        holder.view.imvImage.setOnClickListener {
            Constant.AGENT_ID = dataAgent[position].kd_agen!!
            clickListener ( dataAgent[position], position, "detail" )
        }

        holder.view.txvOptions.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.view.txvOptions)
            popupMenu.inflate(R.menu.menu_options)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_update -> {
                        Constant.AGENT_ID = dataAgent[position].kd_agen!!
                        clickListener ( dataAgent[position], position, "update" )
                    }
                    R.id.action_delete -> {
                        Constant.AGENT_ID = dataAgent[position].kd_agen!!
                        clickListener ( dataAgent[position], position, "delete" )
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

    fun setData(newDataAgent: List<DataAgent>){
        dataAgent.clear()
        dataAgent.addAll(newDataAgent)
        notifyDataSetChanged()
    }

    fun removeAgent(position: Int) {
        dataAgent.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataAgent.size)
    }
}
























