package com.kailin.traffic.ui.bus.realtime

import com.kailin.traffic.R
import com.kailin.traffic.app.MyRecyclerAdapter
import com.kailin.traffic.app.ViewHolder
import com.kailin.traffic.data.bus.estimatedTime.EstimateTimeData
import com.kailin.traffic.data.bus.route.BusStop
import com.kailin.traffic.data.bus.route.BusStopOfRoute
import com.kailin.traffic.databinding.ItemSubRealtimeBinding

class RealtimeSubAdapter : MyRecyclerAdapter<ItemSubRealtimeBinding, BusStop>() {

    override val viewLayoutRes = R.layout.item_sub_realtime
    private val estimateTimeData: MutableList<EstimateTimeData> by lazy { mutableListOf() }
    private lateinit var busStopOfRoute: BusStopOfRoute

    override fun onBindViewHolder(holder: ViewHolder<ItemSubRealtimeBinding>, position: Int) {
        with(holder.binding) {
            val itemData = data[position]
            stopNameText = itemData.StopName.Zh_tw
            estimateTimeData.find {
                it.StopUID == itemData.StopUID && it.RouteUID == busStopOfRoute.RouteUID
            }?.let {
                estimateTimeText = when (it.StopStatus) {
                    1 -> getContext().getString(R.string.estimateTime_Status1)
                    2 -> getContext().getString(R.string.estimateTime_Status2)
                    3 -> getContext().getString(R.string.estimateTime_Status3)
                    4 -> getContext().getString(R.string.estimateTime_Status4)
                    else -> {
                        if (it.EstimateTime < 120) {
                            getContext().getString(R.string.estimateTime_coming)
                        } else {
                            getContext().getString(
                                R.string.estimateTime_estimate, it.EstimateTime / 60
                            )
                        }
                    }
                }
            }
        }
    }

    fun updateEstimateTimeData(newData: MutableList<EstimateTimeData>) {
        calculateDiff(estimateTimeData, newData)
    }

    fun updateBusStopOfRoute(busStopOfRoute: BusStopOfRoute) {
        this.busStopOfRoute = busStopOfRoute
        updateData(busStopOfRoute.Stops)
    }
}