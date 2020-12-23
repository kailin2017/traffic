package com.kailin.traffic.ui.bus.realtime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusRealtimeBinding
import com.kailin.traffic.ui.bus.BusRouteViewModel
import kotlin.math.abs

class RealtimeFragment() : BaseFragment<BusRouteViewModel, FragmentBusRealtimeBinding>() {

    override val viewModel: BusRouteViewModel by activityViewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBusRealtimeBinding.inflate(inflater, container, false)

    override fun initView() {
        with(viewDataBinding.pager) {
            viewModel.busStopOfRoute.observe(this@RealtimeFragment) {
                it.forEach { subRoute ->
                    viewDataBinding.tabLayout.removeAllTabs()
                    viewDataBinding.tabLayout.addTab(
                        viewDataBinding.tabLayout.newTab().setText(subRoute.SubRouteName.Zh_tw)
                    )
                }
                adapter = RealtimeAdapter(requireActivity()).apply { updateData(it) }
                TabLayoutMediator(viewDataBinding.tabLayout, viewDataBinding.pager) { _, _ -> }.attach()
            }
            setPageTransformer { page, position ->
                val absPos = abs(position)
                page.apply {
                    rotation = 0f
                    val scale = if (absPos > 1) 0F else 1 - absPos
                    scaleX = scale
                    scaleY = scale
                }
            }
        }
    }
}