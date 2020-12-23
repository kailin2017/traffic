package com.kailin.traffic.ui.bus.realtime

import com.kailin.traffic.R
import com.kailin.traffic.app.MyRecyclerAdapter
import com.kailin.traffic.app.ViewHolder
import com.kailin.traffic.data.bus.estimatedTime.EstimateTimeData
import com.kailin.traffic.data.bus.route.BusStop
import com.kailin.traffic.databinding.ItemSubRealtimeBinding

class RealtimeSubAdapter : MyRecyclerAdapter<ItemSubRealtimeBinding, BusStop>() {

    private val eData: MutableList<EstimateTimeData> by lazy { mutableListOf() }

    override val viewLayoutRes = R.layout.item_sub_realtime

    override fun onBindViewHolder(holder: ViewHolder<ItemSubRealtimeBinding>, position: Int) {
        val itemData = data[position]
        holder.binding.stopNameText = itemData.StopName.Zh_tw
        eData.find { it.StopUID == itemData.StopUID }?.let {
            getContext().apply {
                holder.binding.estimateTimeText = when (it.StopStatus) {
                    1 -> getString(R.string.estimateTime_Status1)
                    2 -> getString(R.string.estimateTime_Status2)
                    3 -> getString(R.string.estimateTime_Status3)
                    4 -> getString(R.string.estimateTime_Status4)
                    else -> {
                        if (it.EstimateTime < 120) {
                            getString(R.string.estimateTime_coming)
                        } else {
                            getString(
                                R.string.estimateTime_estimate, it.EstimateTime / 60
                            )
                        }
                    }
                }
            }
        }
    }

    fun updateEstimateTimeData(newData: MutableList<EstimateTimeData>) {
        calculateDiff(eData, newData)
    }
}