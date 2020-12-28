package com.kailin.traffic.ui.bus.realtime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusRealtimeBinding
import com.kailin.traffic.ui.bus.BusRouteViewModel
import kotlin.math.abs

class RealtimeFragment : BaseFragment<BusRouteViewModel, FragmentBusRealtimeBinding>() {

    override val viewModel: BusRouteViewModel by activityViewModels()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentBusRealtimeBinding.inflate(inflater, container, false)

    override fun initView() {
        viewDataBinding.apply {
            viewModel.busRouteData.observe(this@RealtimeFragment, {
                toolbar.title = it.RouteName.Zh_tw
                setToolbar(toolbar)
            })
            viewModel.busStopOfRoute.observe(this@RealtimeFragment) {
                pager.adapter = RealtimeAdapter(requireActivity()).apply { updateData(it) }
                it.forEach { _ ->
                    tabLayout.removeAllTabs()
                    tabLayout.addTab(tabLayout.newTab())
                }
                TabLayoutMediator(
                    tabLayout,
                    pager,
                    this@RealtimeFragment::tabConfigurationStrategy
                ).attach()
            }
            pager.setPageTransformer { page, position ->
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

    private fun tabConfigurationStrategy(tab: TabLayout.Tab, p: Int) {
        viewModel.busRouteData.value?.let { busRouteData ->
            viewModel.busStopOfRoute.value?.let {
                tab.text = if (it.size > 2) {
                    getString(
                        R.string.busRoute_tos, it[p].SubRouteName.Zh_tw, when (it[p].Direction) {
                            0 -> busRouteData.DepartureStopNameZh
                            else -> busRouteData.DestinationStopNameZh
                        }
                    )
                } else {
                    getString(
                        R.string.busRoute_to, when (it[p].Direction) {
                            0 -> busRouteData.DepartureStopNameZh
                            else -> busRouteData.DestinationStopNameZh
                        }
                    )
                }
            }
        }

    }
}