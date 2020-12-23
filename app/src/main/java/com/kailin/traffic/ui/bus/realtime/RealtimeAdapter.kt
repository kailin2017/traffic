package com.kailin.traffic.ui.bus.realtime

import androidx.fragment.app.FragmentActivity
import com.kailin.traffic.data.bus.route.BusStopOfRoute
import com.kailin.traffic.widget.recyclerView.MyFragmentStateAdapter

//class RealtimePagerAdapter() : MyRecyclerAdapter<FragmentSubRealtimeBinding, BusStopOfRoute>() {
//
//    override val viewLayoutRes: Int = R.layout.fragment_sub_realtime
//
//    override fun onBindViewHolder(holder: ViewHolder<FragmentSubRealtimeBinding>, position: Int) {
//    }
//}

class RealtimeAdapter(activity: FragmentActivity) :
    MyFragmentStateAdapter<BusStopOfRoute>(activity) {

    override fun createFragment(position: Int) = RealtimeSubFragment.create(data[position])
}