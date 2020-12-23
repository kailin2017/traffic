package com.kailin.traffic.ui.bus.realtime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.data.bus.route.BusStopOfRoute
import com.kailin.traffic.databinding.FragmentSubRealtimeBinding
import com.kailin.traffic.ui.bus.BusRouteViewModel

class RealtimeSubFragment : BaseFragment<BusRouteViewModel, FragmentSubRealtimeBinding>() {

    override val viewModel: BusRouteViewModel by activityViewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSubRealtimeBinding.inflate(inflater, container, false)

    override fun initView() {
        BusStopOfRoute.fromBundle(arguments)?.let {
            val adapter = RealtimeSubAdapter().apply { updateData(it.Stops) }
            viewDataBinding.recyclerView.adapter = adapter
            viewModel.estimateTimeData.observe(this, { adapter.updateEstimateTimeData(it) })
        }
    }

    companion object {

        fun create(busStopOfRoute: BusStopOfRoute): RealtimeSubFragment {
            return RealtimeSubFragment().apply {
                arguments = busStopOfRoute.toBundle()
            }
        }
    }
}