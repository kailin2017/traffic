package com.kailin.traffic.ui.main.home

import android.view.View
import com.kailin.traffic.R
import com.kailin.traffic.app.MyRecyclerAdapter
import com.kailin.traffic.app.ViewHolder
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.databinding.ItemHomeBinding

class HomeRecyclerAdapter(onItemClick: View.OnClickListener) :
    MyRecyclerAdapter<ItemHomeBinding, BusRouteData>(onItemClick) {

    override val viewLayoutRes = R.layout.item_home

    override fun onBindViewHolder(holder: ViewHolder<ItemHomeBinding>, position: Int) {
        with(holder.binding) {
            data = this@HomeRecyclerAdapter.data[position]
//            root.tag = data
        }
    }
}
