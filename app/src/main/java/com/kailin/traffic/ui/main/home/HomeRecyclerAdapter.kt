package com.kailin.traffic.ui.main.home

import com.kailin.traffic.R
import com.kailin.traffic.app.MyRecyclerAdapter
import com.kailin.traffic.app.ViewHolder
import com.kailin.traffic.data.bus.route.BusRouteData
import com.kailin.traffic.databinding.ItemHomeBinding

class HomeRecyclerAdapter : MyRecyclerAdapter<ItemHomeBinding, BusRouteData>() {

    override val viewLayoutRes = R.layout.item_home

    override fun onBindViewHolder(holder: ViewHolder<ItemHomeBinding>, position: Int) {
        holder.binding.data = data[position]
    }
}
