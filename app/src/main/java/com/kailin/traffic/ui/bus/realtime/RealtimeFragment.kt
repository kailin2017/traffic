package com.kailin.traffic.ui.bus.realtime

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kailin.traffic.R
import com.kailin.traffic.app.BaseFragment
import com.kailin.traffic.databinding.FragmentBusRealtimeBinding
import com.kailin.traffic.ui.bus.BusRouteViewModel
import kotlinx.android.synthetic.main.fragment_home.*
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
                setToolbar(toolbar, it.RouteName.Zh_tw, true)
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
            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) =
                    viewModel.selectedPage.postValue(position)
            })
        }
    }

    private fun tabConfigurationStrategy(tab: TabLayout.Tab, p: Int) {
        with(viewModel) {
            busRouteData.value?.let { busRouteData ->
                busStopOfRoute.value?.let {
                    tab.text = getString(
                        R.string.busRoute_to, when (it[p].Direction) {
                            0 -> busRouteData.DepartureStopNameZh
                            else -> busRouteData.DestinationStopNameZh
                        }
                    )
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_reailtime, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar_info -> {
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_navigation_realtime_to_navigation_info)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}